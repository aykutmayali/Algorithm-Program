package com.example.tire.service;

import com.example.tire.domain.Lab;
import com.example.tire.repository.LabRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@ApplicationScoped
public class LabService {

    @Inject
    LabRepository labRepository;

    public List<Lab> listAll() {
        return labRepository.listAll();
    }

    @Transactional
    public Lab create(@Valid @NotNull Lab lab) {
        labRepository.persist(lab);
        return lab;
    }
}
