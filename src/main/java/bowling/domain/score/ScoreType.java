package bowling.domain.score;

import java.util.Arrays;

public enum ScoreType {
    STRIKE(10),
    SPARE(10),
    MISS(-1),
    GUTTER(0);

    private int score;

    ScoreType(int score) {
        this.score = score;
    }

    public static ScoreType of(int number) {
        return Arrays.stream(values())
                .filter(i -> i.getScore() == number)
                .findFirst()
                .orElse(MISS);
    }

    public static ScoreType of(int before, int current) {
        if (before + current == 10) {
            return ScoreType.SPARE;
        }
        return of(current);
    }

    public int getScore() {
        return this.score;
    }
}
