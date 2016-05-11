package com.luckypets.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageSaver {
    private void validateImage(MultipartFile image) throws ImageUploadException {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new ImageUploadException("Only JPG image accepted");
        }
    }

    public void saveImage(String filename, MultipartFile image, HttpServletRequest request) throws ImageUploadException {
        try {
            validateImage(image);
            String webRootPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("Path: " + webRootPath);
            File file = new File(webRootPath + "/resources/" + filename);
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(image.getBytes());
        } catch (FileNotFoundException e) {
            throw new ImageUploadException("Cant open file");
        } catch (IOException e) {
            throw new ImageUploadException("Cant write image to file");
        }
    }
}
