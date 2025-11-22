package com.example.tire.service;

import com.example.tire.domain.Lab;
import com.example.tire.domain.TestRun;
import com.example.tire.domain.Tire;
import com.example.tire.dto.CreateTestRunRequest;
import com.example.tire.repository.LabRepository;
import com.example.tire.repository.TestRunRepository;
import com.example.tire.repository.TireRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TestRunServiceTest {

    @Mock
    TestRunRepository testRunRepository;

    @Mock
    LabRepository labRepository;

    @Mock
    TireRepository tireRepository;

    @InjectMocks
    TestRunService testRunService;

    private Lab lab;
    private Tire tire;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lab = new Lab();
        lab.setId(1L);
        lab.setCode("LAB-1");
        lab.setName("Europe Lab");
        lab.setRegion("EU");

        tire = new Tire();
        tire.setId(2L);
        tire.setCode("TY-1");
        tire.setBrand("Goodyear");
    }

    @Test
    void createShouldRequireMetric() {
        when(labRepository.findById(1L)).thenReturn(lab);
        when(tireRepository.findById(2L)).thenReturn(tire);

        CreateTestRunRequest request = new CreateTestRunRequest(1L, 2L, LocalDateTime.now(), "wet", List.of());
        Assertions.assertThrows(IllegalArgumentException.class, () -> testRunService.create(request));
    }

    @Test
    void createShouldPersistWhenValid() {
        when(labRepository.findById(1L)).thenReturn(lab);
        when(tireRepository.findById(2L)).thenReturn(tire);
        when(testRunRepository.persist(any(TestRun.class))).then(invocation -> {
            TestRun tr = invocation.getArgument(0);
            tr.setId(5L);
            return null;
        });

        CreateTestRunRequest request = new CreateTestRunRequest(1L, 2L, LocalDateTime.now(), "dry",
                List.of(new CreateTestRunRequest.MetricPayload("wetGrip", 80.0, "score")));
        TestRun created = testRunService.create(request);
        Assertions.assertNotNull(created.getMetrics());
        Assertions.assertFalse(created.getMetrics().isEmpty());
        Assertions.assertEquals(5L, created.getId());
    }
}
