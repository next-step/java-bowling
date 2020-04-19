package bowling.domain.frame;

import bowling.domain.score.Score;

public class FinalFrame extends Frame {
    private static final int NONE_TRY_COUNT = 0;
    private static final int FIRST_TRY_COUNT = 1;
    private static final int SECOND_TRY_COUNT = 2;

    public FinalFrame() {
        super();
    }

    @Override
    public boolean isThrowable() {
        if (points.getTryCount() == NONE_TRY_COUNT || points.getTryCount() == FIRST_TRY_COUNT) {
            return true;
        }
        if (points.isSpare() || points.isDoubleStrike()) {
            return true;
        }
        return false;
    }

    @Override
    public void addScore() {
        if (points.isFirstStrike() || points.isSpare()) {
            this.score = makeScore();
        }
    }

    private Score makeScore() {
        if (points.isFirstStrike()) {
            return Score.ofStrike();
        }
        if (points.getTryCount() == SECOND_TRY_COUNT && points.isSpare() && !points.isFirstStrike()) {
            return Score.ofSpare();
        }
        return Score.ofMiss(points.getSum());
    }
}
