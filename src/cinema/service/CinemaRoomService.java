package cinema.service;

import cinema.entity.CinemaRoom;
import cinema.entity.Seat;
import cinema.exception.SeatNotAvailableException;
import cinema.repository.CinemaRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaRoomService {

    private final CinemaRoomRepository cinemaRoomRepository;

    public CinemaRoomService(CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    public CinemaRoom getCinemaRoomWithAvailableSeats() {
        CinemaRoom cinemaRoom = cinemaRoomRepository.getCinemaRoom();
        List<Seat> availableSeats = cinemaRoom.seats().stream()
                .filter(Seat::isAvailable)
                .collect(Collectors.toList());
        return new CinemaRoom(cinemaRoom.rows(), cinemaRoom.columns(), availableSeats);
    }

    public Seat getSeat(int row, int column) {
        return cinemaRoomRepository.getCinemaRoom().getSeat(row, column);
    }

    public void occupySeat(Seat seat) {
        validateSeatAvailability(seat);
        seat.setAvailable(false);
    }

    public void releaseSeat(Seat seat) {
        seat.setAvailable(true);
    }

    private void validateSeatAvailability(Seat seat) {
        if (!seat.isAvailable()) {
            throw new SeatNotAvailableException();
        }
    }

}
