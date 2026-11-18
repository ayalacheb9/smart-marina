package com.xyz.pedestal.exception;

public class PedestalNotFoundException extends RuntimeException {
    public PedestalNotFoundException(Long id) {
        super("Borne introuvable : " + id);
    }
}