package bowling.dto;

public class BowlingGameRequest {

    private String playerName;

    public BowlingGameRequest(String playerName) {
        this.playerName = playerName;
    }

    public String playerName() {
        return playerName;
    }
}
