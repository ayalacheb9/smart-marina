package com.xyz.pedestal.exception;

public class PortServiceUnavailableException extends RuntimeException {
    public PortServiceUnavailableException() {
        super("port-service indisponible");
    }
}