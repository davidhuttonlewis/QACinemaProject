package com.qa.cinema.services;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.qa.cinema.models.Screen;
import com.qa.cinema.models.ScreenType;
import com.qa.cinema.repositories.ScreenRepository;
import com.qa.cinema.util.JSONCreator;

public class ScreenService {
	
	private static final Logger LOGGER = Logger.getLogger(ScreenService.class.getName());

    @Inject
    private ScreenRepository screenRepository;
    
    @Inject
    private JSONCreator json;
	
	public Response find(Integer id) {
		Screen screen = screenRepository.find(id);
        if (screen == null)
            return Response.status(NOT_FOUND).build();
        return Response.ok(json.toJSON(screen)).build();
	}
	
	public Response findAll() {
    	List<Screen> screen = screenRepository.findAll();
        if (screen.isEmpty())
            return Response.status(NO_CONTENT).build();
        return Response.ok(json.toJSON(screen)).build();
	}
	
	public Response create(Screen screen, UriInfo uriInfo) {
        screen = screenRepository.create(screen);
        URI createdURI = uriInfo.getBaseUriBuilder().path(screen.getId().toString()).build();
        return Response.created(createdURI).build();
	}
	
	public Response destroy(Integer id) {
        screenRepository.destroy(id);
        return Response.noContent().build();
	}
	
	public Response update(Integer id, ScreenType type, Integer numberOfSeats, Boolean accessibility) {
		LOGGER.info("ScreenService update params id:" + id + " type:" + type + " numberOfSeats:" + numberOfSeats + " accessibility:" + accessibility);
		Screen screen = screenRepository.find(id);
		LOGGER.info("ScreenService update screen:" + screen);
        if (screen == null) {
        	LOGGER.info("ScreenService update null screen");
            return Response.status(NOT_FOUND).build();
        }
        LOGGER.info("ScreenService update Screen::setType");
        screen.setType(type);
        LOGGER.info("ScreenService update Screen::setNumberOfSeats");
        screen.setNumberOfSeats(numberOfSeats);
        LOGGER.info("ScreenService update setAccessibility");
        screen.setAccessibility(accessibility);

        LOGGER.info("ScreenService update ScreenRepository::update");
        screenRepository.update(screen);

        LOGGER.info("ScreenService update return");
        return Response.noContent().build();
	}
	
}
