package com.xyz.consumption.dto;

import com.xyz.consumption.entity.ResourceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

public record ConsumptionRequest(
        @Size(max = 64) String measurementId,
        @NotNull Long pedestalId,
        @NotNull ResourceType resource,
        @NotNull @Positive BigDecimal quantity,
        @NotNull Instant measuredAt
) {}