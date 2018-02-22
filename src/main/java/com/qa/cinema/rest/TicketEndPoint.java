package com.qa.cinema.rest;

import com.qa.cinema.models.Ticket;
import com.qa.cinema.repositories.TicketRepository;
import com.qa.cinema.util.JSONCreator;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.awt.*;
import java.net.URI;
import java.util.logging.Logger;
import java.util.List;

@Path("/ticket")
public class TicketEndPoint {

    private static final Logger LOGGER = Logger.getLogger(TicketEndPoint.class.getName());

    @Inject
    private TicketRepository ticketRepository;

    @Inject
    private JSONCreator json;

    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getTicket(@PathParam("id") @Min(1) Integer id) {
        LOGGER.info("are you here");
        Ticket ticket = ticketRepository.find(id);
        LOGGER.info("in get single ticket");
        if (ticket == null) {
            LOGGER.info("no this ticket");
            return Response.status(NOT_FOUND).build();
        }
        LOGGER.info("ticket");
        return Response.ok(json.toJSON(ticket)).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllTickets() {
        LOGGER.info("in getting all tickets");
        List<Ticket> tickets = ticketRepository.findAll();
        LOGGER.info("got tickets");
        if (tickets.isEmpty()) {
            LOGGER.info("nothing in here");
            return Response.status(NO_CONTENT).build();
        }
        LOGGER.info("got some tickets");
        return Response.ok(json.toJSON(tickets)).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createTicket(Ticket ticket, @Context UriInfo uriInfo) {
        LOGGER.info("in create method");
        ticket = ticketRepository.create(ticket);
        LOGGER.info("ticket created");
        URI createdURI = uriInfo.getBaseUriBuilder().path(ticket.getId().toString()).build();
        LOGGER.info("ticket done");
        return Response.created(createdURI).build();
    }

    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteTicket(@PathParam("id") @Min(1) Integer id) {
        ticketRepository.delete(id);
        return Response.noContent().build();
    }

}
