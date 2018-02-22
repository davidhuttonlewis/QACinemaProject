package com.qa.cinema.repositories;

import com.qa.cinema.models.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class TicketRepository {

  @PersistenceContext(unitName = "cinemaPU")
  private EntityManager em;

  public Ticket find(Integer id) { return em.find(Ticket.class, id);}

  public List<Ticket> findAll() {
      return em.createQuery("SELECT ticket FROM Ticket ticket ORDER BY ticket.id", Ticket.class).getResultList();
  }

  @Transactional(REQUIRED)
  public Ticket create(@NotNull Ticket ticket) {
      em.persist(ticket);
      return ticket;
  }

  @Transactional(REQUIRED)
  public void delete(@NotNull Integer id) {
      em.remove(em.getReference(Ticket.class, id));
  }
}
