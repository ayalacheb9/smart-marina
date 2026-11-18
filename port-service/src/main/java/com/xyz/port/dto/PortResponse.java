package com.xyz.port.dto;

import com.xyz.port.entity.Port;

public record PortResponse(Long id, String name, String city, int capacity) {
    public static PortResponse from(Port p) {
        return new PortResponse(p.getId(), p.getName(), p.getCity(), p.getCapacity());
    }
}