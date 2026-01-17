package cinema.repository;

import cinema.entity.CinemaRoom;
import cinema.entity.Seat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CinemaRoomRepository {

    private final CinemaRoom cinemaRoom;

    public CinemaRoomRepository() {
        this.cinemaRoom = generateCinemaRoom();
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    private CinemaRoom generateCinemaRoom() {
        int rows = 9;
        int columns = 9;
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                Seat seat = new Seat(row, column, generateTicketPrice(row));
                seats.add(seat);
            }
        }
        return new CinemaRoom(rows, columns, seats);
    }

    private int generateTicketPrice(int row) {
        // if row is 1 to 4, price is 10, else price is 8
        return (row <= 4) ? 10 : 8;
    }

}
