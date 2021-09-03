package cinema;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    CinemaSeats availableSeats = new CinemaSeats(9, 9);
    PurchaseHandler purchaseHandler = new PurchaseHandler();

    @GetMapping("/seats")
    public CinemaSeats getAvailableSeats() {
        return this.availableSeats;
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam (required = false) Object... password) {
        try {
            for (Object obj : password) {
                if (obj instanceof String && obj != null) {
                    if (((String)obj).equalsIgnoreCase("super_secret")) {
                        return new ResponseEntity<>(new StatsForTheManager(purchaseHandler, availableSeats), HttpStatus.OK);
                    }
                }
            }
            return new ResponseEntity<>(new ErrorInfo("The password is wrong!"),
                    HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorInfo("The password is wrong!"),
                    HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody TokenRequest request) {
        UUID returnedToken = request.getToken();
        try {
            if (purchaseHandler.isValidToken(returnedToken)) {
                availableSeats.addSeat(purchaseHandler.getTicketOfToken(returnedToken).getRow(), purchaseHandler.getTicketOfToken(returnedToken).getColumn());
                return new ResponseEntity<>(purchaseHandler.refund(returnedToken), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ErrorInfo("Wrong token!"),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorInfo("Uh no, something in the return post mapper went wrong" + e),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody SeatRequest request) {
        try {
            int row = request.getRow();
            int column = request.getColumn();
            if (row <= 9 && column <= 9 && row > 0 && column > 0) {
                if (availableSeats.isAvilable(row, column)) {
                    availableSeats.removeSeat(row, column);
                    return new ResponseEntity<>(purchaseHandler.makePurchase(row, column), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ErrorInfo("The ticket has been already purchased!")
                            , HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(new ErrorInfo("The number of a row or a column is out of bounds!"),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorInfo("Something else went wrong in the postmapper"),
                    HttpStatus.BAD_REQUEST);
        }
    }
}