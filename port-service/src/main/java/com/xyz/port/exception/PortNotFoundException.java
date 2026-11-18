package com.xyz.port.exception;

public class PortNotFoundException extends RuntimeException {
    public PortNotFoundException(Long id) {
        super("Port introuvable : " + id);
    }
}