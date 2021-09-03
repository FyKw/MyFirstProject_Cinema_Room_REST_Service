package cinema;

import java.util.UUID;

public class Purchase {
    private UUID token;
    private Ticket ticket;


    public Purchase (Ticket ticket, UUID token){
        this.token = token;
        this.ticket = ticket;
    }



    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
