package bowling.refactoring.domain.state;

import bowling.refactoring.domain.frame.*;

public class Running implements State {

    private static final String RUNNING_EXCEPTION = "아직 끝나지 않은 프레임입니다.";

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isEndedCalculateScore() {
        return false;
    }

    @Override
    public void calculateBonusScore(Frame nextFrame) {
        throw new UnsupportedOperationException(RUNNING_EXCEPTION);
    }

    @Override
    public int totalScore() {
        throw new UnsupportedOperationException(RUNNING_EXCEPTION);
    }
}
