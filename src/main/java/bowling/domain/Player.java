package bowling.domain;

public class Player {

    private static final int MAX_NAME_LENGTH = 3;

    private final String playerName;
    private final int position;

    public Player(String playerName, int position) {
        this.playerName = playerName;
        this.position = position;
    }

    public static Player of(String playerName, int position) {
        if (playerName.length() != MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 3글자여야 합니다.");
        }
        return new Player(playerName, position);
    }

    public String name() {
        return playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }

    public int nextPosition() {
        return position + 1;
    }

    public boolean isSamePosition(int position) {
        return this.position == position;
    }
}
