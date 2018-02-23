package com.qa.cinema.services;

import com.qa.cinema.models.Showing;
import com.qa.cinema.models.Ticket;
import com.qa.cinema.models.TicketType;
import com.qa.cinema.repositories.ShowingRepository;
import com.qa.cinema.repositories.TicketRepository;
import com.qa.cinema.rest.TicketEndPoint;
import com.qa.cinema.util.JSONCreator;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Stateless
@Default
public class TicketService {

    private static final Logger LOGGER = Logger.getLogger(TicketEndPoint.class.getName());

    @Inject
    private TicketRepository ticketRepository;

    @Inject
    private ShowingRepository showingRepository;

    @Inject
    private JSONCreator json;

    public Response getTicket(Integer id) {
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


    public Response createTicket(Ticket ticket, UriInfo uriInfo) {
        LOGGER.info("in create method");
        ticket = ticketRepository.create(ticket);
        LOGGER.info("ticket created  " + ticket);
        URI createdURI = uriInfo.getBaseUriBuilder().path(ticket.getId().toString()).build();
        LOGGER.info("ticket done +   " + createdURI);
        return Response.created(createdURI).build();
    }


    public Response deleteTicket(Integer id) {
        ticketRepository.delete(id);
        return Response.noContent().build();
    }


    public Response updateTicket(Integer id, TicketType type, Integer showingId) {

        Ticket ticket = ticketRepository.find(id);
        Showing showing = showingRepository.find(showingId);

        if (ticket == null) {
            Response.status(NOT_FOUND).build();
        }

        ticket.setType(type);
        ticket.setPrice(type.getPrice());
        ticket.setShowing(showing);

        ticketRepository.update(ticket);

        return Response.noContent().build();
    }

}
