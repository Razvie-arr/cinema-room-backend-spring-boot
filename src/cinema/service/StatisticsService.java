package cinema.service;

import cinema.dto.StatisticsResponse;
import cinema.entity.Ticket;
import cinema.exception.WrongPasswordException;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final static String DUMMY_PASSWORD = "super_secret";

    private final CinemaRoomService cinemaRoomService;
    private final TicketService ticketService;

    public StatisticsService(CinemaRoomService cinemaRoomService, TicketService ticketService) {
        this.cinemaRoomService = cinemaRoomService;
        this.ticketService = ticketService;
    }

    public StatisticsResponse generateStatistics(String password) {
        if (!isAuthorized(password)) {
            throw new WrongPasswordException();
        }

        int income = calculateCurrentIncome();
        int availableSeatsCount = cinemaRoomService.getAvailableSeats().size();
        int purchasedSeatsCount = cinemaRoomService.getPurchasedSeats().size();
        return new StatisticsResponse(income, availableSeatsCount, purchasedSeatsCount);
    }

    private int calculateCurrentIncome() {
        return ticketService.getAllTickets().stream()
                .mapToInt(Ticket::price)
                .sum();
    }

    private boolean isAuthorized(String password) {
        return DUMMY_PASSWORD.equals(password);
    }

}
