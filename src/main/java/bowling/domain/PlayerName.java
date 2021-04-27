package bowling.domain;

public class PlayerName {

    private static final String PLAYER_NAME_CONDITION = "^[a-zA-Z]{3}$";

    private final String name;

    private PlayerName(String name) {
        this.name = name;
    }

    public static PlayerName wrap(String name) {
        validate(name);
        return new PlayerName(name);
    }

    private static void validate(String name) {
        if(!name.matches(PLAYER_NAME_CONDITION)) {
            throw new IllegalArgumentException("플레이어 이름은 알파벳 세 글자여야 합니다.");
        }
    }

    public String export() {
        return name;
    }
}
