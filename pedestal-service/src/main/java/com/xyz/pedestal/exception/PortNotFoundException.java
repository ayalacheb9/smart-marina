package com.xyz.pedestal.exception;

public class PortNotFoundException extends RuntimeException {
    public PortNotFoundException(Long id) {
        super("Le port " + id + " n'existe pas");
    }
}