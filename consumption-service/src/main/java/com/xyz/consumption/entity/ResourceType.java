package com.xyz.consumption.entity;

public enum ResourceType {
    ELECTRICITY("kWh"),
    WATER("m3"),
    GAS("m3");

    private final String unit;

    ResourceType(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}