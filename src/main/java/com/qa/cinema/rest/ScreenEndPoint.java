package com.qa.cinema.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;
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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createScreen(Screen screen, @Context UriInfo uriInfo) {
        screen = screenRepository.create(screen);
        URI createdURI = uriInfo.getBaseUriBuilder().path(screen.getId().toString()).build();
        return Response.created(createdURI).build();
    }
    
	
	@DELETE
    @Path("/{id : \\d+}")
    public Response deleteBook(@PathParam("id") @Min(1) Integer id) {
        screenRepository.destroy(id);
        return Response.noContent().build();
    }

	@PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateScreen(@FormParam("id") Integer id, @FormParam("type") ScreenType type,
                                 @FormParam("numberOfSeats") Integer numberOfSeats,
                                 @FormParam("accessibility") Boolean accessibility) {

        Screen screen = screenRepository.find(id);
        if (screen == null) {
            Response.status(NOT_FOUND).build();
        }
        
        screen.setType(type);
        screen.setNumberOfSeats(numberOfSeats);
        screen.setAccessiblity(accessibility);
        
        screenRepository.update(screen);
    
        return Response.noContent().build();
    }
}
