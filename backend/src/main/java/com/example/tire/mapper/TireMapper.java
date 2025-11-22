package com.example.tire.mapper;

import com.example.tire.domain.Tire;
import com.example.tire.dto.TireDto;

public final class TireMapper {

    private TireMapper() {
    }

    public static TireDto toDto(Tire tire) {
        return new TireDto(tire.getId(), tire.getCode(), tire.getBrand());
    }
}
