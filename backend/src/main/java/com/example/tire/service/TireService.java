package com.example.tire.service;

import com.example.tire.domain.Tire;
import com.example.tire.repository.TireRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@ApplicationScoped
public class TireService {

    @Inject
    TireRepository tireRepository;

    public List<Tire> listAll() {
        return tireRepository.listAll();
    }

    @Transactional
    public Tire create(@Valid @NotNull Tire tire) {
        tireRepository.persist(tire);
        return tire;
    }
}
