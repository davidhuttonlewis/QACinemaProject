package com.qa.cinema.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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
import com.qa.cinema.services.ScreenService;

@Path("/screen")
public class ScreenEndpoint {
	
    @Inject
    private ScreenService screenService;

    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getScreen(@PathParam("id") @Min(1) Integer id) {
    	return screenService.find(id);
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    public Response getScreens() {
    	return screenService.findAll();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createScreen(Screen screen, @Context UriInfo uriInfo) {
    	return screenService.create(screen, uriInfo);
    }
    
	
	@DELETE
    @Path("/{id : \\d+}")
    public Response deleteBook(@PathParam("id") @Min(1) Integer id) {
		return screenService.destroy(id);
    }

	@PUT
    @Path("/{id : \\d+}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateScreen(@PathParam("id") Integer id, @FormParam("type") ScreenType type,
                                 @FormParam("numberOfSeats") Integer numberOfSeats,
                                 @FormParam("accessibility") Boolean accessibility) {

        return screenService.update(id, type, numberOfSeats, accessibility);
    }
}
