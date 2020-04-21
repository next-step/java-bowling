package bowling.domain.state;

import bowling.domain.Score;

public class Strike extends Finished {
    private static final int STRIKE_SCORE = 10;
    private Score firstScore;

    public Strike() {
        this.firstScore = new Score(STRIKE_SCORE);
    }

    @Override
    public boolean isEnableCalculate() {
        return false;
    }

    @Override
    public int getScore() {
        return super.getScore();
    }

    @Override
    public int getFirstScore() {
        return firstScore.getScore();
    }

    @Override
    public boolean isStrike() {
        return true;
    }
}
