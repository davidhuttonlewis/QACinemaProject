package com.qa.cinema.rest;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qa.cinema.models.Showing;
import com.qa.cinema.repositories.ShowingRepository;
import com.qa.cinema.util.JSONCreator;

@Path("/showing")
public class ShowingEndpoint {

	@Inject
	private ShowingRepository showingRepository;
	
	@Inject
	private JSONCreator json;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllShowings() {

    	List<Showing> showings = showingRepository.findAll();
        if (showings.isEmpty())
            return Response.status(NOT_FOUND).build();
        return Response.ok(json.toJSON(showings)).build();
	}

}
