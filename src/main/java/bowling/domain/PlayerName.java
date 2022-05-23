package bowling.domain;

public class PlayerName {
    private String playerName;

    public PlayerName(String playerName) {
        if (playerName.length() != 3) {
            throw new IllegalArgumentException("Player name should be 3 letter but, : " + playerName);
        }
            this.playerName = playerName;
    }
//
//    String playerName() {
//        return this.playerName;
//    }

    @Override
    public String toString() {
        return this.playerName;
    }
}
