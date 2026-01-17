package cinema.service;

import cinema.dto.SeatPurchaseRequest;
import cinema.entity.Cinema;
import cinema.entity.Seat;
import cinema.exception.InvalidSeatException;
import cinema.exception.SeatNotAvailableException;
import cinema.repository.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public Cinema getCinemaWithAllSeats() {
        return cinemaRepository.getMainCinema();
    }

    public Cinema getCinemaWithAvailableSeats() {
        Cinema cinema = getCinemaWithAllSeats();
        List<Seat> availableSeats = cinema.seats().stream()
                .filter(seat -> !seat.isPurchased())
                .collect(Collectors.toList());
        return new Cinema(cinema.rows(), cinema.columns(), availableSeats);
    }

    public Seat purchaseSeat(SeatPurchaseRequest requestedSeat) {
        int row = requestedSeat.row();
        int column = requestedSeat.column();

        if (!isSeatValid(row, column)) {
            throw new InvalidSeatException();
        }

        if (!isSeatAvailable(row, column)) {
            throw new SeatNotAvailableException();
        }

        Seat seatToPurchase = getCinemaWithAllSeats().getSeat(row, column);
        seatToPurchase.setPurchased(true);
        return seatToPurchase;
    }

    private boolean isSeatAvailable(int row, int column) {
        return getCinemaWithAllSeats().getSeat(row, column).isPurchased();
    }

    private boolean isSeatValid(int row, int column) {
        Cinema cinema = getCinemaWithAllSeats();
        return (row >= 1) &&
                (row <= cinema.rows()) &&
                (column >= 1) &&
                (column <= cinema.columns());
    }

}
