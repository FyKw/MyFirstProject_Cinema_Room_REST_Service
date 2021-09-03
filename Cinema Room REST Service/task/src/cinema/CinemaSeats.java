package cinema;

import java.util.ArrayList;

public class CinemaSeats {
    private int total_rows;
    private int total_columns;
    private ArrayList<Seat> available_seats = new ArrayList<>();

    public CinemaSeats(int x, int y) {
        this.total_rows = x;
        this.total_columns = y;
        ArrayList<Seat> temp = new ArrayList<>();
        for (int i = 1; i < x + 1; i++) {
            for (int j = 1; j < y + 1; j++) {
                temp.add(new Seat(i, j));
            }
        }
        setAvailable_seats(temp);
    }

    public ArrayList<Seat> getAvailable_seats() {
        return this.available_seats;
    }

    public void setAvailable_seats(ArrayList<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public Seat getSeat(int col, int row) {
        for (Seat seat : available_seats) {
            if (seat.getRow() == col && seat.getColumn() == row) {
                return seat;
            }
        }
        return null;
    }

    public boolean removeSeat(int row, int col) {
        for (Seat s : available_seats) {
            if (s.getRow() == row && s.getColumn() == col) {
                Seat se = new Seat(row, col);
                available_seats.remove(s);
                return true;
            }
        }
        return false;
    }

    public void addSeat(int row, int col){
        available_seats.add(new Seat(row, col));
    }

    public boolean isAvilable(int row, int col) {
        for (Seat s : available_seats) {
            if (s.getRow() == row && s.getColumn() == col) {
                return true;
            }
        }
        return false;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }
}
