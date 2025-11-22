package com.example.tire.repository;

import com.example.tire.domain.TestMetric;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestMetricRepository implements PanacheRepositoryBase<TestMetric, Long> {
}
