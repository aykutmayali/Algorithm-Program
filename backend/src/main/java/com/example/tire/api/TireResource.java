package com.example.tire.api;

import com.example.tire.domain.Tire;
import com.example.tire.dto.TireDto;
import com.example.tire.mapper.TireMapper;
import com.example.tire.service.TireService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/tires")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TireResource {

    @Inject
    TireService tireService;

    @GET
    public List<TireDto> listTires() {
        return tireService.listAll().stream().map(TireMapper::toDto).collect(Collectors.toList());
    }

    @POST
    public Response createTire(@Valid Tire tire) {
        Tire created = tireService.create(tire);
        return Response.created(URI.create("/api/tires/" + created.getId())).entity(TireMapper.toDto(created)).build();
    }
}
