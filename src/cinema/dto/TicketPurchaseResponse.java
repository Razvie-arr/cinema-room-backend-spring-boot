package cinema.dto;

import cinema.entity.Ticket;

public record TicketPurchaseResponse(String token, Ticket ticket) {

}
