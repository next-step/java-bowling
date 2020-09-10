package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.score.BonusFrameScore;

public class BonusFrame extends Frame {

    public BonusFrame(int initAttempt) {
        super(BonusFrameScore.clear(), initAttempt);
    }

    @Override
    void adjustAttempt() {
        attempt.tried();
    }

    @Override
    public boolean match(FrameType type) {
        return FrameType.BONUS == type;
    }

    @Override
    public boolean canCalculateScore() {
        return false;
    }
}
