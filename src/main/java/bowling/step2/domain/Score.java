package bowling.step2.domain;

import java.util.regex.Pattern;

public class Score {
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    static final String SCORE_EXCEPTION = "볼링점수가 잘못 입력되었습니다.";


    private final String score;


    public Score(String score) {
        validate(score);
        this.score = score;
    }

    private void validate(String score) {
        if (!score.equals(STRIKE) && !score.equals(SPARE) && !Pattern.matches("[0-9]", score)) {
            throw new IllegalArgumentException(SCORE_EXCEPTION);
        }
    }

    public boolean isStrike() {
        return this.score.equals(STRIKE);
    }

    public boolean isSpare() {
        return this.score.equals(SPARE);
    }

    public String score() {
        return score;
    }
}
