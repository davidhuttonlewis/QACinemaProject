package com.qa.cinema.rest;

import com.qa.cinema.models.Screen;
import com.qa.cinema.models.ScreenType;
import com.qa.cinema.models.Showing;
import com.qa.cinema.models.Ticket;
import com.qa.cinema.models.TicketType;
import com.qa.cinema.repositories.ShowingRepository;
import com.qa.cinema.repositories.TicketRepository;
import com.qa.cinema.services.TicketService;
import com.qa.cinema.util.JSONCreator;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.awt.*;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.Date;
import java.util.List;

@Path("/ticket")
public class TicketEndPoint {

    private static final Logger LOGGER = Logger.getLogger(TicketEndPoint.class.getName());

    @Inject
    private TicketService ticketService;

    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getTicket(@PathParam("id") @Min(1) Integer id) {
        LOGGER.info("are you here");

        LOGGER.info("in get single ticket");

        LOGGER.info("ticket");
        return ticketService.getTicket(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllTickets() {
        LOGGER.info("in getting all tickets");

        LOGGER.info("got tickets");

        LOGGER.info("got some tickets");
        return ticketService.getAllTickets();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createTicket(Ticket ticket, @Context UriInfo uriInfo) {
        LOGGER.info("in create method");

        LOGGER.info("ticket created");

        LOGGER.info("ticket done");
        return ticketService.createTicket(ticket, uriInfo);
    }

    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteTicket(@PathParam("id") @Min(1) Integer id) {
        return ticketService.deleteTicket(id);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateTicket(@FormParam("id") Integer id, @FormParam("type") TicketType type,
                                 @FormParam("showing") Integer showingId) {
    
        return ticketService.updateTicket(id, type, showingId);
    }

}
