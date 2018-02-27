package com.qa.cinema.services;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.qa.cinema.models.Screen;
import com.qa.cinema.models.Showing;
import com.qa.cinema.repositories.ScreenRepository;
import com.qa.cinema.repositories.ShowingRepository;
import com.qa.cinema.util.JSONCreator;
import java.util.logging.Logger;



public class ShowingService {
	
	private static final Logger LOGGER = Logger.getLogger(ScreenService.class.getName());
	
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
		
		if(checkClash(showing.getTime(), showing.getScreen()))
		{
		showing = showingRepository.create(showing);
		URI createdURI = uriInfo.getBaseUriBuilder().path(showing.getId().toString()).build();
		return Response.created(createdURI).build();
		}else {
			return Response.status(NOT_FOUND).build();
		}
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
//	        if(checkClash(date, screen))
//	       {
	        showing.setFilm(film);
	        showing.setScreen(screen);
	        showing.setTime(date);
	        
	        showingRepository.update(showing);
	    
	        return Response.noContent().build();
//	        }else {
//	        	return Response.notModified().build();
//	        }
	}
	
	public boolean checkClash(Date date, Screen screen) {
		
		LOGGER.info("###DATE ENTERED###" + date.getTime());
		LOGGER.info("###SCREEN ENTERED###" + screen.getId());
		List<Showing> showings = showingRepository.findAll();
		for (int i = 0; i < showings.size(); i++) {
			LOGGER.info("###DATE CHECKING###" + showings.get(i).getTime().getTime());
			LOGGER.info("###SCREEN CHECKING###" + showings.get(i).getScreen().getId());
			if((showings.get(i).getTime().getTime() == date.getTime()) || (date.getTime() <= showings.get(i).getEndTime().getTime()) && (showings.get(i).getScreen().getId() == screen.getId())) {
				LOGGER.info("###INSIDE IF###");
				return false;
			}		
		}
		return true;		
	}
	
}
