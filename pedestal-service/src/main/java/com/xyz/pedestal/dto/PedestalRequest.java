package com.xyz.pedestal.dto;

import com.xyz.pedestal.entity.PedestalStatus;
import jakarta.validation.constraints.*;

public record PedestalRequest(
        @NotBlank @Size(max = 50) String serialNumber,
        @NotBlank @Size(max = 100) String name,
        @NotNull Long portId,
        @NotBlank @Size(max = 50) String pontoon,
        @NotNull PedestalStatus status
) {}