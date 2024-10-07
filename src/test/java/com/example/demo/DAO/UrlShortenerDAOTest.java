package com.example.demo.DAO;

import com.example.demo.Errors.UrlShorteningException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UrlShortenerDAOTest {
    @Test
    void itShouldSave(){
        //given
        Map<String,String> urlsOne = new HashMap<>();
        String fullUrlOne = "www.exampleOne.com";
        String shortUrlOne = "demo.ly/69ddx547";
        String fullUrlTwo = "exampleTwo.com";
        String shortUrlTwo = "demo.ly/69ddn547";
        String fullUrlThree = "http/www.exampleThree.com";
        String shortUrlThree = "demo.ly/69ddxk47";
        urlsOne.put(fullUrlOne,shortUrlOne);
        urlsOne.put(fullUrlTwo,shortUrlTwo);
        urlsOne.put(fullUrlThree,shortUrlThree);

        //when
        UrlShortenerDAO urlShortenerDAO = new UrlShortenerDAO();
        for (Map.Entry<String, String> entry : urlsOne.entrySet()) {
            String fullUrl = entry.getKey();
            String shortUrl = entry.getValue();
            urlShortenerDAO.save(fullUrl,shortUrl);
        }

        //then
        for (Map.Entry<String, String> entry : urlsOne.entrySet()) {
            String fullUrl = entry.getKey();
            String shortUrl = entry.getValue();
            String retrievedUrl = urlShortenerDAO.get(fullUrl);
            assertEquals(shortUrl, retrievedUrl);
            System.out.println("Input Full URL: " + fullUrl);
            System.out.println("Expected Short URL: " + shortUrl);
            System.out.println("Retrieved Short URL: " + retrievedUrl);
        }
    }

    @Test
    void itShouldNotSave(){
        //given
        String fullUrl = "www.One.com";
        String shortUrlOne = "demo.ly/69547";
        String shortUrlTwo = "demo.ly/6ddndf547";

        //when
        UrlShortenerDAO urlShortenerDAO = new UrlShortenerDAO();
        urlShortenerDAO.save(fullUrl,shortUrlOne);
        UrlShorteningException exception = assertThrows(UrlShorteningException.class, () -> {
            urlShortenerDAO.save(fullUrl, shortUrlTwo);
        });

        //then
            assertEquals("This url is already saved", exception.getMessage());
            System.out.println("Input Full URL: " + fullUrl);
            System.out.println("Input Short URL: " + shortUrlTwo);
            System.out.println("Exception message : " + exception.getMessage());

    }

    @Test
    void itShouldGet(){
        //given
        Map<String,String> urlsTwo = new HashMap<>();
        String fullUrlOne = "https/www.exampleOne.com";
        String shortUrlOne = "demo.ly/69ddx547";
        String fullUrlTwo = "https/exampleTwo.com";
        String shortUrlTwo = "demo.ly/69ddn547";
        String fullUrlThree = "https/www.exampleThree.com";
        String shortUrlThree = "demo.ly/69ddxk47";
        urlsTwo.put(fullUrlOne,shortUrlOne);
        urlsTwo.put(fullUrlTwo,shortUrlTwo);
        urlsTwo.put(fullUrlThree,shortUrlThree);

        //when
        UrlShortenerDAO urlShortenerDAO = new UrlShortenerDAO();
        for (Map.Entry<String, String> entry : urlsTwo.entrySet()) {
            String fullUrl = entry.getKey();
            String shortUrl = entry.getValue();
            urlShortenerDAO.save(fullUrl,shortUrl);
        }

        //then
        for (Map.Entry<String, String> entry : urlsTwo.entrySet()) {
            String fullUrl = entry.getKey();
            String shortUrl = entry.getValue();
            String retrievedUrl = urlShortenerDAO.get(fullUrl);
            assertEquals(shortUrl, retrievedUrl);
            System.out.println("Input Full URL: " + fullUrl);
            System.out.println("Expected Short URL: " + shortUrl);
            System.out.println("Retrieved Short URL: " + retrievedUrl);
        }
    }

    @Test
    void itShouldNotGet(){
        //given
        String fullUrlOne = "OneExample.com";
        String fullUrlTwo = "TwoExample.com";
        String shortUrl = "demo.ly/69ddndf547";

        //when
        UrlShortenerDAO urlShortenerDAO = new UrlShortenerDAO();
        urlShortenerDAO.save(fullUrlOne,shortUrl);
        UrlShorteningException exception = assertThrows(UrlShorteningException.class, () -> {
            urlShortenerDAO.get(fullUrlTwo);
        });

        //then
        assertEquals("There is no saved shortened url for your provided full url", exception.getMessage());
        System.out.println("Input Full URL: " + fullUrlOne);
        System.out.println("Input Another full URL: " + fullUrlTwo);
        System.out.println("Exception message : " + exception.getMessage());

    }
}