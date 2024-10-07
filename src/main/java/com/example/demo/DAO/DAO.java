package com.example.demo.DAO;

import java.util.Optional;

public interface DAO<T>{
    String get (String fullUrl);
    void save(String fullUrl,String shortUrl);
}
