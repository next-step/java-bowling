package bowling.domain;

import bowling.exception.PlayerException;

public class Player {
    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!name.matches("^[a-zA-Z]{3}$")) {
            throw new PlayerException("플레이어 이름은 3개의 영어 문자여야 합니다.");
        }
    }
}
