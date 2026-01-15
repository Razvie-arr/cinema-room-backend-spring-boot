package cinema.entity;

import java.util.List;

public record Cinema(int rows, int columns, List<Seat> seats) {

}
