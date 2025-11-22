package com.example.tire.mapper;

import com.example.tire.domain.TestMetric;
import com.example.tire.domain.TestRun;
import com.example.tire.dto.TestMetricDto;
import com.example.tire.dto.TestRunDto;

import java.util.List;
import java.util.stream.Collectors;

public final class TestRunMapper {

    private TestRunMapper() {
    }

    public static TestRunDto toDto(TestRun testRun) {
        List<TestMetricDto> metricDtos = testRun.getMetrics().stream()
                .map(TestRunMapper::toMetricDto)
                .collect(Collectors.toList());
        return new TestRunDto(testRun.getId(), LabMapper.toDto(testRun.getLab()),
                TireMapper.toDto(testRun.getTire()), testRun.getTestDate(), testRun.getTrackType(), metricDtos);
    }

    public static TestMetricDto toMetricDto(TestMetric metric) {
        return new TestMetricDto(metric.getId(), metric.getName(), metric.getValue(), metric.getUnit());
    }
}
