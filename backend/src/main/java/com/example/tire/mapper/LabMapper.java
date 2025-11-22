package com.example.tire.mapper;

import com.example.tire.domain.Lab;
import com.example.tire.dto.LabDto;

public final class LabMapper {

    private LabMapper() {
    }

    public static LabDto toDto(Lab lab) {
        return new LabDto(lab.getId(), lab.getCode(), lab.getName(), lab.getRegion());
    }
}
