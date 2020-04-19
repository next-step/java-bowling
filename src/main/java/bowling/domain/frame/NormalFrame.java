package bowling.domain.frame;

import bowling.domain.score.Score;

public class NormalFrame extends Frame {
    private static final int MAX_TRYABLE_COUNT = 2;

    public NormalFrame() {
        super();
    }

    @Override
    public boolean isThrowable() {
        if (points.getTryCount() >= MAX_TRYABLE_COUNT || points.isFirstStrike()) {
            return false;
        }
        return true;
    }

    @Override
    public void addScore() {
        if (!isThrowable()) {
            this.score = makeScore();
        }
    }

    private Score makeScore() {
        if (points.isFirstStrike()) {
            return Score.ofStrike();
        }
        if (points.isSpare()) {
            return Score.ofSpare();
        }
        return Score.ofMiss(points.getSum());
    }
}
