package bowling.domain.frame;

import bowling.domain.score.Score;

public class FinalFrame extends Frame {

    public FinalFrame() {
        super();
    }

    @Override
    public boolean isThrowable() {
        if (points.getTryCount() == 0 || points.getTryCount() == 1) {
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
        if (points.getTryCount() == 2 && points.isSpare() && !points.isFirstStrike()) {
            return Score.ofSpare();
        }
        return Score.ofMiss(points.getSum());
    }
}
