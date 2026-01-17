package cinema.service;

import cinema.dto.TicketPurchaseRequest;
import cinema.dto.TicketPurchaseResponse;
import cinema.entity.Seat;
import cinema.entity.Ticket;
import cinema.exception.WrongTokenException;
import cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    private final CinemaRoomService cinemaRoomService;
    private final TicketRepository ticketRepository;

    public TicketService(CinemaRoomService cinemaRoomService, TicketRepository ticketRepository) {
        this.cinemaRoomService = cinemaRoomService;
        this.ticketRepository = ticketRepository;
    }

    public TicketPurchaseResponse purchaseTicket(TicketPurchaseRequest ticketPurchaseRequest) {
        Seat seat = cinemaRoomService.getSeat(ticketPurchaseRequest.row(), ticketPurchaseRequest.column());
        cinemaRoomService.occupySeat(seat);

        Ticket ticket = new Ticket(seat);
        String token = generateToken();
        ticketRepository.saveTokenAndTicket(token, ticket);

        return new TicketPurchaseResponse(token, ticket);
    }

    public Ticket returnTicket(String token) {
        Ticket ticket = ticketRepository.getTicketByToken(token);
        if (ticket == null) {
            throw new WrongTokenException();
        }

        Seat seat = cinemaRoomService.getSeat(ticket.row(), ticket.column());
        cinemaRoomService.releaseSeat(seat);

        ticketRepository.removeTicketByToken(token);
        return ticket;
    }

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

}
