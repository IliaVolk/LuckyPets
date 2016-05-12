package com.springapp.mvc;

import com.luckypets.service.ImageSaver;
import com.luckypets.service.ImageUploadException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;


public class ImageSavingTests {

    private ImageSaver imageSaver;
    private HttpServletRequest request;
    private MultipartFile image;

    @Before
    public void before() throws IOException {
        imageSaver = new ImageSaver();
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        request = Mockito.mock(HttpServletRequest.class);
        image = Mockito.mock(MultipartFile.class);
        Mockito.when(servletContext.getRealPath("/")).
                thenReturn("D:\\JavaIdeaProjects\\LuckyPets\\src\\test\\java\\com\\springapp\\mvc");


        Mockito.when(session.getServletContext()).thenReturn(servletContext);


        Mockito.when(request.getLocale()).thenReturn(Locale.ENGLISH);
        Mockito.when(request.getSession()).thenReturn(session);


        Mockito.when(image.getBytes()).thenReturn(new byte[]{0});
    }


    @Test
    public void testImageSaver() throws IOException, ImageUploadException {


        Mockito.when(image.getContentType()).thenReturn("image/jpeg");


        imageSaver.saveImage("1.jpg", image, request);
    }

    @Test
    public void testImageWrongType() throws IOException, ImageUploadException {

        Mockito.when(image.getContentType()).thenReturn("image/gif");


        try {
            imageSaver.saveImage("1.jpg", image, request);
        } catch (ImageUploadException e) {
            Assert.assertEquals("Only JPG image accepted", e.getMessage());
            return;
        }
        Assert.assertEquals("This code should not be executed", "This code executed");
    }
}
