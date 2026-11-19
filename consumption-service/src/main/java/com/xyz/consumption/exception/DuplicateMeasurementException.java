package com.xyz.consumption.exception;

public class DuplicateMeasurementException extends RuntimeException {
    public DuplicateMeasurementException(String measurementId) {
        super("Mesure déjà enregistrée : " + measurementId);
    }
}