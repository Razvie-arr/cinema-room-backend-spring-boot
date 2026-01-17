package cinema.controller;

import cinema.dto.TicketPurchaseRequest;
import cinema.dto.TicketPurchaseResponse;
import cinema.dto.TicketReturnResponse;
import cinema.dto.TokenRequest;
import cinema.entity.Ticket;
import cinema.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<TicketPurchaseResponse> purchaseTicket(@RequestBody TicketPurchaseRequest ticketPurchaseRequest) {
        TicketPurchaseResponse ticketPurchaseResponse = ticketService.purchaseTicket(ticketPurchaseRequest);
        return ResponseEntity.ok().body(ticketPurchaseResponse);
    }

    @PostMapping("/return")
    public ResponseEntity<TicketReturnResponse> returnTicket(@RequestBody TokenRequest tokenRequest) {
        Ticket ticket = ticketService.returnTicket(tokenRequest.token());
        return ResponseEntity.ok().body(new TicketReturnResponse(ticket));
    }


}
