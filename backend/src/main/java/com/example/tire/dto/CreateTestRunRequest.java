package com.example.tire.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateTestRunRequest(
        @NotNull Long labId,
        @NotNull Long tireId,
        @NotNull LocalDateTime testDate,
        @NotNull String trackType,
        @NotEmpty List<@Valid MetricPayload> metrics
) {
    public record MetricPayload(
            @NotNull String name,
            @NotNull Double value,
            @NotNull String unit
    ) {}
}
