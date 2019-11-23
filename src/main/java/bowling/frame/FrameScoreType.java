package bowling.frame;

import java.util.Arrays;

import static bowling.score.rollling.Pin.MAX_PIN_NUMBER;

public enum FrameScoreType {
    STRIKE(1, MAX_PIN_NUMBER), SPARE(2, MAX_PIN_NUMBER), MISS(2), PENDING(1),
    ;
    private int size;
    private Integer score;

    FrameScoreType(int size) {
        this.size = size;
    }

    FrameScoreType(int size, int score) {
        this.size = size;
        this.score = score;
    }

    public static FrameScoreType get(int size, int score) {
        return Arrays.stream(values())
                .filter(gameType -> gameType.size == size)
                .filter(gameType -> gameType.score == null || gameType.score == score)
                .findFirst().orElse(PENDING);
    }
}
