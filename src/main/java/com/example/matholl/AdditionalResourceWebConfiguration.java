package com.example.matholl;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        try {
            registry.addResourceHandler("/upload/**").addResourceLocations(new File(".").getCanonicalPath() + "/matholl/src/main/resources/upload/static/img/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
