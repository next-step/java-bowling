package bowling.domain;

import java.util.Arrays;
import java.util.Optional;

public enum FrameType {

    STRIKE("X", 10),
    SPARE("/", -1),
    MISS("", -1),
    GUTTER("-", 0);

    private String code;
    private int score;

    public static Optional<FrameType> findByScore(int score) {
        return Arrays.stream(values())
                .filter(s -> s.score == score)
                .findFirst();
    }

    FrameType(String code, Integer score) {
        this.code = code;
        this.score = score;
    }

    public String getCode() {
        return code;
    }
}
