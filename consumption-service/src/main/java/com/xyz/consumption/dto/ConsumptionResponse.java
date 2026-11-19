package com.xyz.consumption.dto;

import com.xyz.consumption.entity.Consumption;
import com.xyz.consumption.entity.ResourceType;

import java.math.BigDecimal;
import java.time.Instant;

public record ConsumptionResponse(Long id, String measurementId, Long pedestalId,
                                  ResourceType resource, BigDecimal quantity, String unit,
                                  Instant measuredAt, Instant receivedAt) {

    public static ConsumptionResponse from(Consumption c) {
        return new ConsumptionResponse(c.getId(), c.getMeasurementId(), c.getPedestalId(),
                c.getResource(), c.getQuantity(), c.getUnit(),
                c.getMeasuredAt(), c.getReceivedAt());
    }
}