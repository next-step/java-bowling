package bowling.domain;

public class PlayerName {
    public static final String INVALID_PLAYER_NAME_ERR_MSG = "플레이어의 이름은 영문 3글자로 입력해주세요.";
    private final String value;

    private PlayerName(String value) {
        if (value.length() != 3) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_ERR_MSG);
        }

        if (!value.matches("^[a-zA-Z]*${3}")) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_ERR_MSG);
        }

        this.value = value;
    }

    public static PlayerName valueOf(String value) {
        return new PlayerName(value);
    }
}
