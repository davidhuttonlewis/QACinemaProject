package com.qa.cinema.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.qa.cinema.models.Screen;
import com.qa.cinema.repositories.ScreenRepository;
import com.qa.cinema.util.JSONCreator;

@Path("/screen")
public class ScreenEndPoint {

    @Inject
    private ScreenRepository screenRepository;
    
    @Inject
    private JSONCreator json;

    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getScreen(@PathParam("id") @Min(1) Integer id) {
        Screen screen = screenRepository.find(id);
        if (screen == null)
            return Response.status(NOT_FOUND).build();
        return Response.ok(json.toJSON(screen)).build();
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    public Response getScreens() {
    	if (screenRepository != null) {
	    	List<Screen> screen = screenRepository.findAll();
	        if (screen.isEmpty())
	            return Response.status(NOT_FOUND).build();
	        return Response.ok(json.toJSON(screen)).build();
    	}
    	return Response.ok("Fucked up").build();
    }
}
