package bowling.step2.domain;

public class Score {
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    public static final String SCORE_EXCEPTION = "볼링점수가 잘못 입력되었습니다.";
    private final String score;


    public Score(String score) {
        validate(score);
        this.score = score;
    }

    private void validate(String score) {
        if (!score.equals(STRIKE) && !score.equals(SPARE) && !isScoreNumber(score)) {
            throw new IllegalArgumentException(SCORE_EXCEPTION);
        }
    }

    private boolean isScoreNumber(String score) {
        try {
            int number = Integer.parseInt(score);
            return number >= 0 && number <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isStrike() {
        return this.score.equals(STRIKE);
    }

    public boolean isSpare() {
        return this.score.equals(SPARE);
    }
}
