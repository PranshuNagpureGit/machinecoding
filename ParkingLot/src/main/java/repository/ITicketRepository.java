package repository;

import model.Ticket;

public interface ITicketRepository {
    Ticket persist(Ticket newTicket);
}
