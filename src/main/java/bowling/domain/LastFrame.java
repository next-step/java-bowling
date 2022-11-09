package bowling.domain;

import bowling.global.BowlingConst;

public class LastFrame extends Frame {

    private static final int LAST_FRAME_SIZE = 3;

    @Override
    public boolean isRemainChance() {
        if (this.scores.size() < LAST_FRAME_SIZE - 1) {
            return true;
        }
        if (this.scores.size() == LAST_FRAME_SIZE - 1) {
            return isRemainThirdTimeChance();
        }
        return false;
    }

    private boolean isRemainThirdTimeChance() {
        Score first = this.scores.first();
        Score second = this.scores.second();
        if (first.isStrike() || second.isStrike() || ((first.value() + second.value()) == BowlingConst.SCORE_STRIKE)) {
            return true;
        }
        return false;
    }
}
