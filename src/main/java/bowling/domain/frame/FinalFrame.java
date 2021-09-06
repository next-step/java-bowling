package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class FinalFrame extends Frame {
    private static final int MAX_NUMBER_OF_TURN_IN_FINAL_FRAME = 3;

    public FinalFrame(Score... scores) {
        super(scores);
    }

    public FinalFrame(Scores scores) {
        super(scores);
    }

    public static FinalFrame of(int... scores) {
        return new FinalFrame(Scores.of(scores));
    }

    public static FinalFrame of(Score... scores) {
        return new FinalFrame(scores);
    }

    protected void validate() {
        if (scores.isOverTenInFinalFrame()) {
            throw new IllegalArgumentException(OUT_OF_FRAME_SCORE_EXCEPTION_STATEMENT);
        }
    }

    public String toScoreSymbol() {
        if (scores.numberOfTurnInFrame() <= 2) {
            return super.toScoreSymbol();
        }

        Score firstTryScore = scores.firstTryScore();
        Score secondTryScore = scores.secondTryScore();
        Score thirdTryScore = scores.thirdTryScore();

        return changeScoreToSpareInFinalFrame(firstTryScore, secondTryScore, thirdTryScore);
    }

    private String changeScoreToSpareInFinalFrame(final Score firstTryScore, final Score secondTryScore,
        final Score thirdTryScore) {
        if (!firstTryScore.equals(TEN_SCORE) && firstTryScore.isEqualTenAfterAdd(secondTryScore)) {
            return changeScoreToSpare(firstTryScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(thirdTryScore);
        }

        if (!secondTryScore.equals(TEN_SCORE) && secondTryScore.isEqualTenAfterAdd(thirdTryScore)) {
            return changeScoreToSymbol(firstTryScore) + SEPARATOR_SYMBOL + changeScoreToSpare(secondTryScore);
        }
        return changeScoreToSymbol(firstTryScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(secondTryScore)
            + SEPARATOR_SYMBOL + changeScoreToSymbol(thirdTryScore);
    }

    @Override
    public boolean isNextFrame() {
        if (scores.numberOfTurnInFrame() == MAX_NUMBER_OF_TURN_IN_FINAL_FRAME) {
            return true;
        }

        if (scores.numberOfTurnInFrame() < 2) {
            return false;
        }

        Score firstTryScore = scores.firstTryScore();
        Score secondTryScore = scores.secondTryScore();

        return firstTryScore.isLessThanTenAfterAdd(secondTryScore);
    }
}
