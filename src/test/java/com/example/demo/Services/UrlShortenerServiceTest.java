package com.example.demo.Services;

import com.example.demo.DAO.UrlShortenerDAO;
import com.example.demo.Errors.UrlShorteningException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UrlShortenerServiceTest {

    @Test
    void urlShouldBeValid(){
        //given
        List<String> urls = new ArrayList<>();
        String urlOne = "www.example.com";
        String urlTwo ="example.com";
        String urlThree="http://www.exampsdsdgle.com";
        urls.add(urlOne);
        urls.add(urlTwo);
        urls.add(urlThree);

        //when
        UrlShortenerDAO urlShortenerDAO = new UrlShortenerDAO();
        UrlShortenerService urlShortenerService = new UrlShortenerService(urlShortenerDAO);

        //then
        urls.stream().forEach(url -> {
            String shortUrl = urlShortenerService.saveShortUrl(url);
            assertNotNull(shortUrl);
            System.out.println("Input Full URL: " + url);
            System.out.println("Generated Short URL: " + shortUrl);
        });
    }


    @Test
    void urlShouldNotBeValid(){
        //given
        List<String> urls = new ArrayList<>();
        String urlOne = "sdfsdfsd";
        String urlTwo ="e.xample.com";
        String urlThree="httasdadp/www.example.com";
        urls.add(urlOne);
        urls.add(urlTwo);
        urls.add(urlThree);

        //when
        UrlShortenerDAO urlShortenerDAO = new UrlShortenerDAO();
        UrlShortenerService urlShortenerService = new UrlShortenerService(urlShortenerDAO);
        urls.stream().forEach(url -> {UrlShorteningException exception = assertThrows(UrlShorteningException.class, () -> {
            urlShortenerService.saveShortUrl(url);
        });

            //then
            assertEquals("Invalid URL provided.", exception.getMessage());
            System.out.println("Input Full URL: " + url);
            System.out.println("Exception message : " + exception.getMessage());
        });
    }

}