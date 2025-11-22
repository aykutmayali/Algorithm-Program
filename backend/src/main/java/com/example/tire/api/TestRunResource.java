package com.example.tire.api;

import com.example.tire.dto.CreateTestRunRequest;
import com.example.tire.dto.GripAverageDto;
import com.example.tire.dto.TestRunDto;
import com.example.tire.mapper.TestRunMapper;
import com.example.tire.service.TestRunService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/tests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestRunResource {

    @Inject
    TestRunService testRunService;

    @GET
    public List<TestRunDto> list(@QueryParam("labId") Long labId,
                                 @QueryParam("from") LocalDateTime from,
                                 @QueryParam("to") LocalDateTime to) {
        return testRunService.findByFilters(labId, from, to)
                .stream()
                .map(TestRunMapper::toDto)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return testRunService.findById(id)
                .map(testRun -> Response.ok(TestRunMapper.toDto(testRun)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response create(@Valid CreateTestRunRequest request) {
        var created = testRunService.create(request);
        return Response.created(URI.create("/api/tests/" + created.getId()))
                .entity(TestRunMapper.toDto(created)).build();
    }

    @GET
    @Path("/stats/average-grip")
    public List<GripAverageDto> averageGrip() {
        return testRunService.averageWetGripPerLab();
    }
}
