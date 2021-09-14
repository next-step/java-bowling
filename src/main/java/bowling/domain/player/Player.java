package bowling.domain.player;

import bowling.domain.Results;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.Objects;

public class Player {
    private static final String PLAYER_NAME_NULL_OR_EMPTY_EXCEPTION_STATEMENT = "플레이어 이름이 널이거나 빈 문자입니다";
    private static final String PLAYER_NAME_LENGTH_EXCEPTION_STATEMENT = "플레이어 이름의 길이가 3자가 아닙니다";
    private static final int PLAYER_NAME_LENGTH = 3;

    private final String name;

    private Player(String name) {
        validate(name);
        this.name = name;
    }

    public static Player from(String name) {
        return new Player(name);
    }

    private void validate(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAME_NULL_OR_EMPTY_EXCEPTION_STATEMENT);
        }

        if (name.length() != PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH_EXCEPTION_STATEMENT);
        }
    }

    public String name() {
        return name;
    }

    public List<String> results(Frames frames) {
        Results results = Results.from(frames);
        return results.results();
    }
}
