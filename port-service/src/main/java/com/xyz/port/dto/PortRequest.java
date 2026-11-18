package com.xyz.port.dto;

import jakarta.validation.constraints.*;

public record PortRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 100) String city,
        @Positive int capacity
) {}