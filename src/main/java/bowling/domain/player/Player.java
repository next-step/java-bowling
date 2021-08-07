package bowling.domain.player;

import bowling.utils.StringUtils;

public class Player {
    private static final int LIMIT_LENGTH_OF_NAME = 3;

    private final String name;

    public Player(String name) {
        validate(name);

        this.name = name;
    }

    private void validate(String name) {
        if (StringUtils.isEmpty(name) || name.length() != LIMIT_LENGTH_OF_NAME) {
            throw new IllegalArgumentException("이름은 " + LIMIT_LENGTH_OF_NAME + " 자 이어야 합니다.");
        }
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }
}
