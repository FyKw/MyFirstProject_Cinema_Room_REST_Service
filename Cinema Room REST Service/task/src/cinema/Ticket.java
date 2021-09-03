package cinema;

public class Ticket {

    private int row;
    private int column;
    private int price;

    public Ticket(int row, int column){
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;

}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


}


