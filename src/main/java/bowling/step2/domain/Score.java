package bowling.step2.domain;

public class Score {
    private static final String STRIKE = "10";
    private static final String ZERO = "0";
    static final String SCORE_SIZE_EXCEPTION = "볼링점수는 0-10사이의 숫자가 입력되어야 합니다.";
    static final String SCORE_FORMAT_EXCEPTION = "볼링점수가 숫자가 입력되어야 합니다.";


    private final Integer score;


    public Score(String score) {
        validate(score);
        this.score = Integer.parseInt(score);
    }

    private void validate(String score) {
        try {
            int num = Integer.parseInt(score);
            if (num < 0 || num > 10) {
                throw new IllegalArgumentException(SCORE_SIZE_EXCEPTION);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(SCORE_FORMAT_EXCEPTION);
        }

    }

    public boolean isStrike() {
        return this.score.equals(STRIKE);
    }

    public boolean isZero() {
        return this.score.equals(ZERO);
    }

    public int score() {
        return score;
    }
}
