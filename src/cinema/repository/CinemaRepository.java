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
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                Seat seat = new Seat(i, j);
                seats.add(seat);
            }
        }
        return new Cinema(rows, columns, seats);
    }

}
