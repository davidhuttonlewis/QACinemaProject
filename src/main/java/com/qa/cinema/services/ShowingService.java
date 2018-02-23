package com.qa.cinema.services;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.qa.cinema.models.Screen;
import com.qa.cinema.models.Showing;
import com.qa.cinema.repositories.ScreenRepository;
import com.qa.cinema.repositories.ShowingRepository;
import com.qa.cinema.util.JSONCreator;



public class ShowingService {
	
	@Inject
	private ShowingRepository showingRepository;
	
	@Inject
	private ScreenRepository screenRepository;

	@Inject
	private JSONCreator json;
	
	public Response getShowing(Integer id) {
		Showing showing = showingRepository.find(id);
		if (showing == null)
			return Response.status(NOT_FOUND).build();
		return Response.ok(json.toJSON(showing)).build();
	}
	
	public Response getAllShowings() {
		List<Showing> showings = showingRepository.findAll();
		if (showings.isEmpty())
			return Response.status(NOT_FOUND).build();
		return Response.ok(json.toJSON(showings)).build();
	}

	public Response createShowing(Showing showing, UriInfo uriInfo) {
		showing = showingRepository.create(showing);
		URI createdURI = uriInfo.getBaseUriBuilder().path(showing.getId().toString()).build();
		return Response.created(createdURI).build();
	}
	
	public Response deleteShowing(Integer id) {
		showingRepository.delete(id);
		return Response.noContent().build();
	}
	
	public Response updateShowing(Integer id, Integer screenId,String time,String film) {
		   Showing showing = showingRepository.find(id);
	       Screen screen = screenRepository.find(screenId);
	        
	        if (showing == null) {
	            Response.status(NOT_FOUND).build();
	        }
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        Date date = null;
			try {
				date = sdf.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        
	        showing.setFilm(film);
	        showing.setScreen(screen);
	        showing.setTime(date);
	        
	        showingRepository.update(showing);
	    
	        return Response.noContent().build();
	}
	
}
