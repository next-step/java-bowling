package bowling.domain;

import java.util.Random;

public class ScoreGenerator {

    private static final Random SCORE;

    static {
        SCORE = new Random();
    }

    public static int getScore() {
        return SCORE.nextInt(11);
    }

    public static int getScoreBySecond(int remainScore) {
        return SCORE.nextInt(remainScore);
    }

}
