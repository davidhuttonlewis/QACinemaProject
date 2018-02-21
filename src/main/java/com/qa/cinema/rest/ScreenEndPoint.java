package com.qa.cinema.rest;

import com.qa.cinema.models.Screen;
import com.qa.cinema.repositories.ScreenRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/screen")
public class ScreenEndPoint {

    @Inject
    private ScreenRepository screenRepository;

    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getScreen(@PathParam("id") @Min(1) Integer id) {
        Screen screen = screenRepository.find(id);
        if (screen == null)
            return Response.status(NOT_FOUND).build();
        return Response.ok(screen).build();
    }

}
