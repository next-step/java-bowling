package domain;

public class Player {
    private static final String REGEX_FOR_PLAYER_NAME = "^[a-zA-Z]{3}$";
    static final String ALERT_INVALID_PLAYER_NAME = "플레이어의 이름은 세 자리의 영문만 가능합니다.";

    private final String name;

    private Player(String inputName) {
        validationPlayerName(inputName);
        this.name = inputName.toUpperCase();
    }

    public static Player from(String inputName) {
        return new Player(inputName);
    }

    private void validationPlayerName(String inputName) {
        if (!inputName.matches(REGEX_FOR_PLAYER_NAME)) {
            throw new IllegalArgumentException(ALERT_INVALID_PLAYER_NAME);
        }
    }

    public boolean isSameName(String nameToCompare) {
        return nameToCompare.equals(name);
    }

    public String getName() {
        return name;
    }
}