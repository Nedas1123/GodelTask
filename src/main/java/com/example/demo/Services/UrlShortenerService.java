package com.example.demo.Services;

import com.example.demo.Errors.UrlShorteningException;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.DAO.UrlShortenerDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    private final UrlShortenerDAO urlShortenerDAO;

    @Autowired
    public UrlShortenerService(UrlShortenerDAO urlShortenerDAO) {
        this.urlShortenerDAO = urlShortenerDAO;
    }

    public String saveShortUrl(String fullUrl){
        if(!isUrlValid(fullUrl) || fullUrl.isEmpty()){
            throw new UrlShorteningException("Invalid URL provided.");
    }
        String shortUrl= "demo.ly/" + generateShortUrl(fullUrl);
        urlShortenerDAO.save(fullUrl,shortUrl);
        return shortUrl;
    }

    public String getShortUrl(String fullUrl){
        if(!isUrlValid(fullUrl) || fullUrl.isEmpty()){
            throw new UrlShorteningException("Invalid URL provided.");
        }
        return urlShortenerDAO.get(fullUrl);
    }

    //Generates code from random 8 characters which includes letters and/or numbers
    private String generateShortUrl(String url){
        return new RandomStringGenerator.Builder().withinRange('0','z').filteredBy(c -> Character.isDigit(c) || Character.isAlphabetic(c)).get().generate(8);
    }

    // checks if url is valid
    private Boolean isUrlValid(String url){
        String urlPattern = "^((https?|ftp|smtp):\\/\\/)?(www\\.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$";
        return url.matches(urlPattern);
    }
}
