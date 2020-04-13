package bowling.domain;

public class Player {
    private static final int PLAYER_NAME_LENGTH = 3;
    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() != PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 3글자 입니다.");
        }
    }

    public String getName() {
        return name;
    }
}
