package cinema.entity;

import cinema.exception.InvalidSeatException;

import java.util.List;

public record CinemaRoom(int rows, int columns, List<Seat> seats) {

    public CinemaRoom(int rows, int columns, List<Seat> seats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = List.copyOf(seats);
    }

    public Seat getSeat(int row, int column) {
        validateSeatIndices(row, column);
        int index = (row - 1) * this.columns + (column - 1);
        return seats.get(index);
    }

    private void validateSeatIndices(int row, int column) {
        if (row < 1 || row > rows || column < 1 || column > columns) {
            throw new InvalidSeatException();
        }
    }

}
