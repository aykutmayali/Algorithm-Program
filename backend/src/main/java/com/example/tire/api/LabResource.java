package com.example.tire.api;

import com.example.tire.domain.Lab;
import com.example.tire.dto.LabDto;
import com.example.tire.mapper.LabMapper;
import com.example.tire.service.LabService;
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

@Path("/api/labs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LabResource {

    @Inject
    LabService labService;

    @GET
    public List<LabDto> listLabs() {
        return labService.listAll().stream().map(LabMapper::toDto).collect(Collectors.toList());
    }

    @POST
    public Response createLab(@Valid Lab lab) {
        Lab created = labService.create(lab);
        return Response.created(URI.create("/api/labs/" + created.getId())).entity(LabMapper.toDto(created)).build();
    }
}
