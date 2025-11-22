package com.example.tire.service;

import com.example.tire.domain.Lab;
import com.example.tire.domain.TestMetric;
import com.example.tire.domain.TestRun;
import com.example.tire.domain.Tire;
import com.example.tire.dto.CreateTestRunRequest;
import com.example.tire.dto.GripAverageDto;
import com.example.tire.repository.LabRepository;
import com.example.tire.repository.TestRunRepository;
import com.example.tire.repository.TireRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TestRunService {

    @Inject
    TestRunRepository testRunRepository;

    @Inject
    LabRepository labRepository;

    @Inject
    TireRepository tireRepository;

    public List<TestRun> findByFilters(Long labId, LocalDateTime from, LocalDateTime to) {
        return testRunRepository.findByFilters(labId, from, to);
    }

    public Optional<TestRun> findById(Long id) {
        return testRunRepository.findByIdOptional(id);
    }

    @Transactional
    public TestRun create(@Valid CreateTestRunRequest request) {
        Lab lab = labRepository.findById(request.labId());
        Tire tire = tireRepository.findById(request.tireId());
        if (lab == null || tire == null) {
            throw new IllegalArgumentException("Lab or tire not found");
        }
        if (request.metrics() == null || request.metrics().isEmpty()) {
            throw new IllegalArgumentException("At least one metric is required");
        }

        TestRun testRun = new TestRun();
        testRun.setLab(lab);
        testRun.setTire(tire);
        testRun.setTestDate(request.testDate());
        testRun.setTrackType(request.trackType());

        List<TestMetric> metrics = new ArrayList<>();
        for (CreateTestRunRequest.MetricPayload payload : request.metrics()) {
            TestMetric metric = new TestMetric();
            metric.setTestRun(testRun);
            metric.setName(payload.name());
            metric.setUnit(payload.unit());
            metric.setValue(payload.value());
            metrics.add(metric);
        }
        testRun.setMetrics(metrics);
        testRunRepository.persist(testRun);
        return testRun;
    }

    public List<GripAverageDto> averageWetGripPerLab() {
        return labRepository.listAll().stream()
                .map(lab -> new GripAverageDto(lab.getCode(), testRunRepository.averageWetGripByLab(lab.getId()).orElse(null)))
                .toList();
    }
}
