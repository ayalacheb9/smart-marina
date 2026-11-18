package com.xyz.pedestal.exception;

public class DuplicateSerialNumberException extends RuntimeException {
    public DuplicateSerialNumberException() {
        super("Numéro de série déjà utilisé");
    }
}