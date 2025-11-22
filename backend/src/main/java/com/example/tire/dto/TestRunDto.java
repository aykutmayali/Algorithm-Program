package com.example.tire.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TestRunDto(Long id, LabDto lab, TireDto tire, LocalDateTime testDate, String trackType, List<TestMetricDto> metrics) {}
