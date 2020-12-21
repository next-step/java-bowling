package bowling.domain;

public class PlayerName {
    public static final String INVALID_PLAYER_NAME_ERR_MSG = "플레이어의 이름은 영문 3글자로 입력해주세요.";
    public static final String VALID_INPUT_REGEX = "^[a-zA-Z]{3}";
    private final String value;

    private PlayerName(String value) {
        validateInputValue(value);
        this.value = value;
    }

    private void validateInputValue(String value) {
        if (!value.matches(VALID_INPUT_REGEX)) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_ERR_MSG);
        }
    }

    public static PlayerName valueOf(String value) {
        return new PlayerName(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerName)) return false;

        PlayerName that = (PlayerName) o;

        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public String toString() {
        return "PlayerName{" +
                "value='" + value + '\'' +
                '}';
    }
}
