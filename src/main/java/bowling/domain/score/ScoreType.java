package bowling.domain.score;

import java.util.Arrays;

public enum ScoreType {
    STRIKE(10, "x"),
    SPARE(10, "/"),
    MISS(-1, ""),
    GUTTER(0, "-");

    private int score;
    private String display;

    ScoreType(int score, String display) {
        this.score = score;
        this.display = display;
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

    public static ScoreType of(ScoreType before, int current) {
        if (before.getScore() + current == 10) {
            return ScoreType.SPARE;
        }
        return of(current);
    }

    public int getScore() {
        return this.score;
    }

    public String getDisplay() {
        return display;
    }
}
