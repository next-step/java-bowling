package bowling.step2.domain;

public class Player {
    private final PlayerName playerName;
    
    public Player(final String playerName) {
        this.playerName = new PlayerName(playerName);
    }
}