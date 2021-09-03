package cinema;

import java.util.ArrayList;
import java.util.UUID;

public class PurchaseHandler {

    private ArrayList<Purchase> purchases = new ArrayList<>();

    public PurchaseHandler() {
    }

    public boolean isValidToken(UUID token) {
        for (Purchase purchase : purchases) {
            if (purchase.getToken().equals(token)) return true;
        }
        return false;
    }

    public Purchase_Return refund(UUID token) {

        if (token == null ||  !isValidToken(token)) {
            return null;
        }
        for (Purchase purchase : purchases) {
                if (purchase.getToken().equals(token)) {
                Purchase_Return temp = new Purchase_Return(new Returned_Ticket(purchase.getTicket().getRow(), purchase.getTicket().getColumn()));
                purchases.remove(purchase);
                return temp;

            }
        }
        return null;
    }

    public Purchase makePurchase(int row, int column) {
        Purchase temp = new Purchase(new Ticket(row, column), UUID.randomUUID());
        purchases.add(temp);
        return temp;
    }

    public int getIncome() {
        int income = 0;
        for (Purchase purchase : purchases) {
            income += purchase.getTicket().getPrice();
        }
        return income;
    }

    public int countNumberOfTicketsSold() {
        return purchases.size();
    }

    public Ticket getTicketOfToken(UUID returnedToken) {
        for (Purchase purchase : purchases) {
            if (purchase.getToken().equals(returnedToken))
                return purchase.getTicket();

        } return null;
    }
}

