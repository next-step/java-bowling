package bowling.domain.score;

import java.util.Arrays;

/**
 * Created By mand2 on 2020-12-21.
 */
public enum BowlingScore {

    GUTTER(0, "-"),
    EMPTY(0, ""),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    STRIKE(10, "X"),
    SPARE(10, "/")
    ;

    private int value;
    private String name;

    BowlingScore(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getResultScore(int score, boolean spare) {
        if (spare) {
            return SPARE.name;
        }

        return Arrays.stream(values())
                .filter(bowlingScore -> bowlingScore.value == score)
                .findFirst()
                .map(BowlingScore::getName)
                .orElse("");
    }
    public static BowlingScore getBowlingScore(int score, boolean spare) {
        if (spare) {
            return SPARE;
        }

        return Arrays.stream(values())
                .filter(bowlingScore -> bowlingScore.value == score)
                .findFirst()
                .orElse(BowlingScore.EMPTY);
    }


    public String getName() {
        return name;
    }
}
