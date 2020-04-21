package bowling.domain.state;

import bowling.domain.Score;

public class Finished implements State {
    @Override
    public State bowl(Score score) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public boolean isEnableCalculate() {
        return true;
    }

    @Override
    public int getFirstScore() {
        return 0;
    }

    @Override
    public int getSecondeScore() {
        return 0;
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
