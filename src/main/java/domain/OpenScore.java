package domain;

import java.util.Arrays;

public enum OpenScore {
    ZERO(0, "-"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9");

    private int score;
    private String scoreName;

    OpenScore(int score, String scoreName) {
        this.score = score;
        this.scoreName = scoreName;
    }

    public static OpenScore valueOf(int score) {
        return Arrays.stream(values())
                .filter(openScore -> openScore.score == score)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static String valueOfScoreName(int score) {
        return valueOf(score).getScoreName();
    }

    public String getScoreName() {
        return scoreName;
    }
}
