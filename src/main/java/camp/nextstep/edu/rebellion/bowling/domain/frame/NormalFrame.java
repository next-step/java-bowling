package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.score.NormalFrameScore;

public class NormalFrame extends Frame {

    public NormalFrame(int initAttempt) {
        super(NormalFrameScore.clear(), initAttempt);
    }

    @Override
    void adjustAttempt() {
        if (frameScore.isStrike()) {
            attempt.setNoAttempt();
            return;
        }
        attempt.tried();
    }

    @Override
    public boolean match(FrameType type) {
        return FrameType.NORMAL == type;
    }

    @Override
    public boolean canCalculateScore() {
        return !attempt.hasAttempt() && !bonus.hasChance();
    }
}
