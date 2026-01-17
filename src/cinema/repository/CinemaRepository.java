package cinema.repository;

import cinema.entity.Cinema;
import cinema.entity.Seat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CinemaRepository {

    private final Cinema mainCinema;

    public CinemaRepository() {
        this.mainCinema = generateMainCinema();
    }

    public Cinema getMainCinema() {
        return mainCinema;
    }


    private Cinema generateMainCinema() {
        int rows = 9;
        int columns = 9;
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                Seat seat = new Seat(row, column, generateTicketPrice(row));
                seats.add(seat);
            }
        }
        return new Cinema(rows, columns, seats);
    }

    private int generateTicketPrice(int row) {
        // if row is 1 to 4, price is 10, else price is 8
        return (row <= 4) ? 10 : 8;
    }

}
