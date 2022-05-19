package bowling.domain;

import java.util.Objects;

public class PlayerName {

    private static final int PLAYER_NAME_LENGTH_BOUNDARY = 3;

    private final String playerName;

    private PlayerName(String playerName) {
        if (isNullOrEmpty(playerName)) {
            throw new IllegalArgumentException("사용자의 이름은 공백일 수 없습니다");
        }
        if (isNotThreeLetters(playerName)) {
            throw new IllegalArgumentException("입력하신 " + playerName + "은 3개의 영어로 이루어지지 않았습니다.");
        }
        this.playerName = playerName;
    }

    private boolean isNullOrEmpty(String playerName) {
        return playerName == null || playerName.isEmpty();
    }

    private boolean isNotThreeLetters(String playerName) {
        return playerName.length() != PLAYER_NAME_LENGTH_BOUNDARY;
    }

    public static PlayerName from(String playerName) {
        return new PlayerName(playerName);
    }

    public String playerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName that = (PlayerName) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
