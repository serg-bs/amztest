package com.example.demo.exceptions;

public class CacheException extends RuntimeException {
    String ip;

    public CacheException(String ip) {
        this.ip = ip;
    }

}
