package repository;

import model.Ticket;

public interface ITicketRepository {
    Ticket save(Ticket newTicket);
}
