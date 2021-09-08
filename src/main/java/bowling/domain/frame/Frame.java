package bowling.domain.frame;

import java.util.List;
import java.util.Objects;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public abstract class Frame {
    public static final Score TEN_SCORE = Score.from(10);
    protected static final String OUT_OF_FRAME_SCORE_EXCEPTION_STATEMENT = "프레임 내 점수값의 합이 범위를 벗어났습니다";
    protected static final Score ZERO_SCORE = Score.from(0);
    protected static final String STRIKE_SYMBOL = "X";
    protected static final String SEPARATOR_SYMBOL = "|";
    protected static final String SPARE_SYMBOL = "/";
    protected static final String GUTTER_SYMBOL = "-";

    protected final Scores scores;

    public Frame(Score... scores) {
        this.scores = Scores.of(scores);
    }

    protected Frame(final Scores scores) {
        this.scores = scores;
    }

    public void addScore(Score score) {
        scores.addScore(score);
        validate();
    }

    protected String changeScoreToSymbol(final Score score) {
        if (Objects.isNull(score)) {
            return "";
        }

        if (score.equals(ZERO_SCORE)) {
            return GUTTER_SYMBOL;
        }

        if (score.equals(TEN_SCORE)) {
            return STRIKE_SYMBOL;
        }

        return score.score() + "";
    }

    protected String changeScoreToSpare(final Score firstTryScore) {
        return changeScoreToSymbol(firstTryScore) + SEPARATOR_SYMBOL + SPARE_SYMBOL;
    }

    public String toScoreSymbol() {
        Score firstTryScore = scores.firstTryScore();

        if (scores.numberOfTurnInFrame() <= 1) {
            return changeScoreToSymbol(firstTryScore);
        }

        Score secondTryScore = scores.secondTryScore();
        if (firstTryScore.isEqualTenAfterAdd(secondTryScore)) {
            return changeScoreToSpare(firstTryScore);
        }

        return changeScoreToSymbol(firstTryScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(secondTryScore);
    }

    public List<Score> scores() {
        return scores.scores();
    }

    protected abstract void validate();

    public abstract boolean isNextFrame();
}
