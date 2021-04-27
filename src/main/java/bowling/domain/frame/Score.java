package bowling.domain.frame;

import java.util.Arrays;

public enum Score {
    GUTTER("-", 0),
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    STRIKE("X", 10),
    SPARE("/", 10);

    private String expression;
    private int score;

    Score(String expression, int score) {
        this.expression = expression;
        this.score = score;
    }

    public static Score valueOf(int score) {
        return Arrays.stream(values())
                .filter(i -> i.score == score)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public static Score valueOf(Score previousScore, int score) {
        if (previousScore == STRIKE || previousScore == SPARE) {
            return valueOf(score);
        }
        if (previousScore.score + score == 10) {
            return SPARE;
        }
        return valueOf(score);
    }
}
