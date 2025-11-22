package com.example.tire.repository;

import com.example.tire.domain.Lab;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabRepository implements PanacheRepositoryBase<Lab, Long> {
}
