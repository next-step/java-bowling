package bowling.domain.player;

public class PlayerCount {
    private final int playerCount;

    private PlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public static PlayerCount of(int playerCount) {
        return new PlayerCount(playerCount);
    }

    public static PlayerCount of(String playerCount) {
        return new PlayerCount(Integer.parseInt(playerCount));
    }

    public int toInteger() {
        return this.playerCount;
    }
}
