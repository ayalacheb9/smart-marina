package com.xyz.consumption.exception;

public class PedestalNotFoundException extends RuntimeException {
    public PedestalNotFoundException(Long id) {
        super("La borne " + id + " n'existe pas");
    }
}