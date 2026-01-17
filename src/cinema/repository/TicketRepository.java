package cinema.repository;

import cinema.entity.Ticket;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TicketRepository {

    private final Map<String, Ticket> tokenToTicketMap = new HashMap<>();

    public void saveTokenAndTicket(String token, Ticket ticket) {
        tokenToTicketMap.put(token, ticket);
    }

    public void removeTicketByToken(String token) {
        tokenToTicketMap.remove(token);
    }

    public Ticket getTicketByToken(String token) {
        return tokenToTicketMap.get(token);
    }

}
