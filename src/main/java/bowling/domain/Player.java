package bowling.domain;

public class Player {

    private static final int MAX_NAME_LENGTH = 3;

    private final String playerName;


    public Player(String playerName) {
        if (playerName.length() != MAX_NAME_LENGTH) {
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
