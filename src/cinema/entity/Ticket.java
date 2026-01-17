package cinema.entity;

public record Ticket(int row, int column, int price) {

    public Ticket(Seat seat) {
        this(seat.getRow(), seat.getColumn(), seat.getPrice());
    }

}
