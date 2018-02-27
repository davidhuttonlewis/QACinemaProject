package com.qa.cinema.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.qa.cinema.models.Screen;
import com.qa.cinema.models.ScreenType;
import com.qa.cinema.models.Showing;
import com.qa.cinema.repositories.ScreenRepository;
import com.qa.cinema.repositories.ShowingRepository;
import com.qa.cinema.services.ShowingService;
import com.qa.cinema.util.JSONCreator;

@Path("/showing")
public class ShowingEndpoint {

	@Inject
	private ShowingService showingService;
	
	@GET
	@Path("/{id : \\d+}")
	@Produces(APPLICATION_JSON)
	public Response getShowing(@PathParam("id") @Min(1) Integer id) {
		return showingService.getShowing(id);		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllShowings() {
		return showingService.getAllShowings();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(Showing showing, @Context UriInfo uriInfo) {

		return showingService.createShowing(showing, uriInfo);
	}

	@DELETE
	@Path("/{id : \\d+}")
	public Response deleteBook(@PathParam("id") @Min(1) Integer id) {

		return showingService.deleteShowing(id);
	}
	
	
	@PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateShowing(@FormParam("id") Integer id, @FormParam("film") String film,
                                 @FormParam("screen") Integer screenId,
                                 @FormParam("time") String time) {

		
		return showingService.updateShowing(id, screenId, time, film);
    }

}
