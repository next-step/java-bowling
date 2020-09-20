package bowling.domain;

import java.util.Arrays;

public enum Score {

    NONE(-1, " "),
    GUTTUER(0, "-"),
    SCORE1(1, "1"),
    SCORE2(2, "2"),
    SCORE3(3, "3"),
    SCORE4(4, "4"),
    SCORE5(5, "5"),
    SCORE6(6, "6"),
    SCORE7(7, "7"),
    SCORE8(8, "8"),
    SCORE9(9, "9"),
    STRIKE(10, "X");

    public static final int STRIKE_VALUE = 10;

    private int hit;
    private String score;

    Score(int hit, String score) {
        this.hit = hit;
        this.score = score;
    }

    public boolean checkScore(int hit) {
        return this.hit == hit;
    }

    public String getScore() {
        return score;
    }

    public int getHit() {
        return hit;
    }

    public static Score valueOf(int hit) {
        return Arrays.stream(values())
                .filter(hitValue -> hitValue.checkScore(hit))
                .findAny()
                .orElse(GUTTUER);
    }

}
