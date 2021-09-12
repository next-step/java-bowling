package bowling.domain;

import java.util.List;
import java.util.Objects;

import bowling.domain.frame.Frames;

public class Player {
    private static final String PLAYER_NAME_NULL_OR_EMPTY_EXCEPTION_STATEMENT = "플레이어 이름이 널이거나 빈 문자입니다";
    private static final String PLAYER_NAME_LENGTH_EXCEPTION_STATEMENT = "플레이어 이름의 길이가 3자가 아닙니다";
    private static final int PLAYER_NAME_LENGTH = 3;

    private final String name;
    private final Frames frames;

    private Player(String name, Frames frames) {
        validate(name);
        this.name = name;
        this.frames = frames;
    }

    public static Player from(String name, Frames frames) {
        return new Player(name, frames);
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

    public Frames frames() {
        return frames;
    }

    public List<String> results() {
        return Results.from(frames).results();
    }
}
