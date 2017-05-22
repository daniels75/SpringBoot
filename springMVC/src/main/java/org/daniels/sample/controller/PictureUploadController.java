package org.daniels.sample.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

/**
 * Created by daniels on 05.05.2017.
 */
@Controller
public class PictureUploadController {
    public static final Resource PICTURE_DIR = new FileSystemResource("./pictures");


    @RequestMapping("upload")
    public String uploadPage() {
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(@RequestParam  MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        if (file.isEmpty() || !isImage(file)) {
            redirectAttributes.addFlashAttribute("error", "Please load an image file");
            return "redirect:/upload";
        }
        copyFileToPicture(file);
        return "profile/uploadPage";
    }

    private Resource copyFileToPicture(@RequestParam MultipartFile file) throws IOException {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        File tempFile = File.createTempFile("pic", getFileExtension(fileExtension), PICTURE_DIR.getFile());
        try (InputStream inputStream = file.getInputStream(); OutputStream outputStream = new FileOutputStream(tempFile)) {
            {
                IOUtils.copy(inputStream, outputStream);
            }

        }
        return new FileSystemResource(tempFile);
    }

    @NotNull
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }
}
