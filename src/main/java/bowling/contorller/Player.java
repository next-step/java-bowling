package bowling.contorller;

public class Player {

    private final String playerName;

    public Player(String playerName) {
        if (playerName.length() != 3) {
            throw new IllegalArgumentException("플레이어 이름은 3글자여야 합니다.");
        }
        this.playerName = playerName;
    }

    public static Player of(String playerName) {
        return new Player(playerName);
    }

    public String name() {
        return playerName;
    }
}
