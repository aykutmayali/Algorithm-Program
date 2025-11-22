package com.example.tire.repository;

import com.example.tire.domain.TestRun;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TestRunRepository implements PanacheRepositoryBase<TestRun, Long> {

    public List<TestRun> findByFilters(Long labId, LocalDateTime from, LocalDateTime to) {
        StringBuilder query = new StringBuilder("1=1");
        if (labId != null) {
            query.append(" and lab.id = ?1");
        }
        if (from != null) {
            query.append(" and testDate >= ?2");
        }
        if (to != null) {
            query.append(" and testDate <= ?3");
        }
        return find(query.toString(), labId, from, to).list();
    }

    public Optional<Double> averageWetGripByLab(Long labId) {
        String jpql = "select avg(m.value) from TestMetric m where m.testRun.lab.id = ?1 and lower(m.name) = 'wetgrip'";
        return Optional.ofNullable(getEntityManager().createQuery(jpql, Double.class)
                .setParameter(1, labId)
                .getSingleResult());
    }
}
