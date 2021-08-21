package bowling.player;

import bowling.dto.PlayerDto;

import java.util.Objects;

public class Player {
    private static final int LIMIT_NAME_LENGTH = 3;

    private String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player init(String name) {
        verifyName(Objects.requireNonNull(name));
        return new Player(name);
    }

    private static void verifyName(String name) {
        if (name.length() != LIMIT_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("선수 이름은 %s글자 여야 합니다 !", LIMIT_NAME_LENGTH));
        }
    }

    public PlayerDto convert() {
        return PlayerDto.from(name);
    }
}
