package cinema;

import java.util.UUID;

public class TokenRequest {

    private UUID token;

    public TokenRequest() {
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}

