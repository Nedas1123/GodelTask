package com.example.demo.DAO;

import com.example.demo.Errors.UrlShorteningException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UrlShortenerDAO<T> implements DAO<T> {

    private static final Map<String,String> urls = new HashMap<>();

    @Override
    public String get(String fullUrl) {
        if(!urls.containsKey(fullUrl)){
            throw new UrlShorteningException("There is no saved shortened url for your provided full url");
        }
        return urls.get(fullUrl);
    }

    @Override
    public void save(String fullUrl,String shortUrl) {
        if(urls.containsKey(fullUrl)){
            throw new UrlShorteningException("This url is already saved");
        }
        urls.put(fullUrl,shortUrl);
    }
}
