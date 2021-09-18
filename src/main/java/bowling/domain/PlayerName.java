package bowling.domain;

public class PlayerName {
    private static final int PLAYER_NAME_LENGTH = 3;

    private final String playerName;

    public PlayerName(String playerName) {
        validate(playerName);

        this.playerName = playerName;
    }

    private void validate(String playerName) {
        if (playerName == null || "".equals(playerName) || playerName.length() != PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException("올바른 플레이어 이름을 입력해주세요.");
        }
    }

    public String getValue() {
        return this.playerName;
    }
}
