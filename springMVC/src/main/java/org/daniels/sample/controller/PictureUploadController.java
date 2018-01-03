package org.daniels.sample.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.daniels.sample.configuration.PictureUploadProperties;
import org.daniels.sample.profile.UserProfileSession;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

/**
 * Created by daniels on 05.05.2017.
 */
@Controller
@SessionAttributes("picturePath")
public class PictureUploadController {

    private final Resource pictureDir;
    private final Resource anonymousPicture;
    private final MessageSource messageSource;
    private final UserProfileSession userProfileSession;

    public PictureUploadController(PictureUploadProperties uploadProperties, MessageSource messageSource, UserProfileSession userProfileSession) {
        this.pictureDir = uploadProperties.getUploadPath();
        this.anonymousPicture = uploadProperties.getAnonymousPicture();
        this.messageSource = messageSource;
        this.userProfileSession = userProfileSession;
    }

    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws IOException {
        Resource picturePath = userProfileSession.getPicturePath();
        if (picturePath == null) {
            picturePath = anonymousPicture;
        }
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.getFilename()));
        IOUtils.copy(picturePath.getInputStream(), response.getOutputStream());
    }

    @RequestMapping(value = "/profile", params = {"upload"}, method = RequestMethod.POST)
    public String onUpload(@RequestParam  MultipartFile file, RedirectAttributes redirectAttributes, Model model) throws IOException {
        if (file.isEmpty() || !isImage(file)) {
            redirectAttributes.addFlashAttribute("error", "Please load an image file");
            return "redirect:/profile";
        }
        Resource picturePath = copyFileToPicture(file);
        userProfileSession.setPicturePath(picturePath);

        return "redirect:profile";
    }

    private Resource copyFileToPicture(@RequestParam MultipartFile file) throws IOException {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        File tempFile = File.createTempFile("pic", fileExtension, pictureDir.getFile());
        try (InputStream inputStream = file.getInputStream(); OutputStream outputStream = new FileOutputStream(tempFile)) {
            {
                IOUtils.copy(inputStream, outputStream);
            }

        }
        return new FileSystemResource(tempFile);
    }



    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error", messageSource.getMessage("upload.io.exception", null, locale));
        modelAndView.addObject("profileForm", userProfileSession.toForm());
        return modelAndView;
    }

    @RequestMapping("uploadError")
    public ModelAndView onUploadError(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error", messageSource.getMessage("upload.file.too.big", null, locale));
        modelAndView.addObject("profileForm", userProfileSession.toForm());
        return modelAndView;
    }

    @NotNull
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }
}
