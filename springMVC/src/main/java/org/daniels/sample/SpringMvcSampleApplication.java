package org.daniels.sample;

import org.daniels.sample.configuration.PictureUploadProperties;
import org.daniels.sample.controller.PictureUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.ByteArrayOutputStream;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class SpringMvcSampleApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringMvcSampleApplication.class, args);
	}
}
