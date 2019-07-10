public class PlayerName {
    private static final String REGEX_FOR_PLAYER_NAME = "^[a-zA-Z]{3}$";
    static final String ALERT_INVALID_PLAYER_NAME = "플레이어의 이름은 세 자리의 영문만 가능합니다.";

    private final String name;

    private PlayerName(String inputName) {
        validationPlayerName(inputName);
        this.name = inputName.toUpperCase();
    }

    public static PlayerName from(String inputName) {
        return new PlayerName(inputName);
    }

    private void validationPlayerName(String inputName) {
        if (!inputName.matches(REGEX_FOR_PLAYER_NAME)) {
            throw new IllegalArgumentException(ALERT_INVALID_PLAYER_NAME);
        }
    }

    public boolean isSameName(String nameToCompare) {
        return nameToCompare.equals(name);
    }
}
