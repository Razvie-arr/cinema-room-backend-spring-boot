package cinema.entity;

import java.util.List;

public record Cinema(int rows, int columns, List<Seat> seats) {

    public Seat getSeat(int row, int column) {
        // because the cinema is ordered
        int index = (row - 1) * this.columns + (column - 1);
        return seats.get(index);
    }

}
