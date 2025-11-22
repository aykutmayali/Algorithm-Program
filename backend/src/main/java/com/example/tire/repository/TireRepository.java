package com.example.tire.repository;

import com.example.tire.domain.Tire;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TireRepository implements PanacheRepositoryBase<Tire, Long> {
}
