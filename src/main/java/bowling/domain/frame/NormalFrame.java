package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class NormalFrame extends Frame {
    private static final int MAX_NUMBER_OF_TURN_IN_NORMAL_FRAME = 2;

    private NormalFrame(Score... scores) {
        super(scores);
    }

    private NormalFrame(Scores scores) {
        super(scores);
    }

    public static NormalFrame of(int... scores) {
        return new NormalFrame(Scores.of(scores));
    }

    public static NormalFrame of(Score... scores) {
        return new NormalFrame(scores);
    }

    public static NormalFrame create() {
        return new NormalFrame();
    }

    protected void validate() {
        if (scores.isOverTenInNormalFrame()) {
            throw new IllegalArgumentException(OUT_OF_FRAME_SCORE_EXCEPTION_STATEMENT);
        }
    }

    @Override
    public boolean isNextFrame() {
        if (scores.numberOfTurnInFrame() == MAX_NUMBER_OF_TURN_IN_NORMAL_FRAME) {
            return true;
        }

        Score firstTryScore = scores.firstTryScore();
        return firstTryScore.equals(TEN_SCORE);
    }
}
