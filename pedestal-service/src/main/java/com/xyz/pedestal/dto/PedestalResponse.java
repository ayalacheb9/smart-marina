package com.xyz.pedestal.dto;

import com.xyz.pedestal.entity.Pedestal;
import com.xyz.pedestal.entity.PedestalStatus;

public record PedestalResponse(Long id, String serialNumber, String name,
                               Long portId, String pontoon, PedestalStatus status) {
    public static PedestalResponse from(Pedestal p) {
        return new PedestalResponse(p.getId(), p.getSerialNumber(), p.getName(),
                p.getPortId(), p.getPontoon(), p.getStatus());
    }
}