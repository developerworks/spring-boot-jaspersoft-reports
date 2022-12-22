package com.example.demo7springbootjaspersoftreports.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@Slf4j
public class AppWebConfig implements WebMvcConfigurer {


    /**
     * Add static resources path mappings
     * @param registry ResourceHandlerRegistry
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String reportPath = uploadPath("generated-reports");
        log.info("REPORT PATH: " + reportPath);
        String location = "file:" + reportPath + "/"; // suffix "/" is required, or will cause 404

        registry.addResourceHandler("/generated-reports/**")
                .addResourceLocations(location);
    }


    /**
     * Get absolute path of generated pdf files stored.
     * @param directory upload file path
     * @return The absolute path
     */
    private String uploadPath(String directory) {
        Path uploadDirPath = Paths.get(directory);
        return uploadDirPath.toFile().getAbsolutePath();
    }
}
