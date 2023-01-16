package bowling.refactoring.domain.state;

import bowling.refactoring.domain.frame.*;

public abstract class Finished implements State {

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isEndedCalculateScore() {
        return true;
    }

    @Override
    public void calculateBonusScore(Frame nextFrame) {
    }
}
