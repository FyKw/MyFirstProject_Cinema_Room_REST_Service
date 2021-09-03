package cinema;

import java.util.UUID;

public class Purchase_Return {


    private Returned_Ticket returned_ticket;


    public Purchase_Return(Returned_Ticket ticket) {

        this.returned_ticket = ticket;
    }

    public Returned_Ticket getReturned_ticket() {
        return returned_ticket;
    }

}
