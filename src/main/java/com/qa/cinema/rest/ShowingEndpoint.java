package com.qa.cinema.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
	@Path("/{id : \\d+}")
	@Produces(APPLICATION_JSON)
	public Response getShowing(@PathParam("id") @Min(1) Integer id) {
		Showing showing = showingRepository.find(id);
		if (showing == null)
			return Response.status(NOT_FOUND).build();
		return Response.ok(json.toJSON(showing)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllShowings() {

		List<Showing> showings = showingRepository.findAll();
		if (showings.isEmpty())
			return Response.status(NOT_FOUND).build();
		return Response.ok(json.toJSON(showings)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(Showing showing, @Context UriInfo uriInfo) {
		showing = showingRepository.create(showing);
		URI createdURI = uriInfo.getBaseUriBuilder().path(showing.getId().toString()).build();
		return Response.created(createdURI).build();
	}

	@DELETE
	@Path("/{id : \\d+}")
	public Response deleteBook(@PathParam("id") @Min(1) Integer id) {
		showingRepository.delete(id);
		return Response.noContent().build();
	}

}
