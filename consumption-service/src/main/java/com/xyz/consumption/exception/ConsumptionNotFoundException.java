package com.xyz.consumption.exception;

public class ConsumptionNotFoundException extends RuntimeException {
    public ConsumptionNotFoundException(Long id) {
        super("Mesure introuvable : " + id);
    }
}