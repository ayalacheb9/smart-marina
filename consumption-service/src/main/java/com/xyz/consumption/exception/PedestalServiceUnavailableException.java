package com.xyz.consumption.exception;

public class PedestalServiceUnavailableException extends RuntimeException {
    public PedestalServiceUnavailableException() {
        super("pedestal-service indisponible");
    }
}