package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {

    private final int row;
    private final int column;
    private final int price;
    private boolean isAvailable;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    @JsonIgnore
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
