package bowling.dto;

import java.util.List;

public class BowlingGameRequest {

    private List<String> playerNames;

    public BowlingGameRequest(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public List<String> playerNames() {
        return playerNames;
    }
}
