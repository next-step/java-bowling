package bowling.domain;

import java.util.Arrays;

public enum ScoreMark {
    STRIKE(10, "X"),
    SPARE(10, "/"),
    MISS(-1, ""),
    GUTTER(0, "-");

    private final int score;
    private final String mark;

    ScoreMark(int score, String mark) {
        this.score = score;
        this.mark = mark;
    }

    public static ScoreMark of(int score, boolean isFirstHit) {
        ScoreMark findScoreMark = Arrays.stream(values())
                .filter(scoreMark -> scoreMark.score == score)
                .findFirst()
                .orElse(MISS);
        if (!isFirstHit && findScoreMark == STRIKE) {
            return SPARE;
        }
        return findScoreMark;
    }

    public String mark() {
        return this.mark;
    }
}
