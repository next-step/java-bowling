package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class LastStrikeNormalScore extends AbstractNormalScore {

    public LastStrikeNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        return normalFrame.getTotalNumberOfPin();
    }

    @Override
    protected boolean checkValid() {
        if (!normalFrame.isCompleted()) {
            return false;
        }
        if (normalFrame.getNextFrame().isNone()) {
            return false;
        }
        if (normalFrame.getNextFrame().isStrike()) {
            if (normalFrame.getNextFrame().getNextFrame().isNone()) {
                return false;
            }
        } else {
            if (!normalFrame.getNextFrame().isCompleted()) {
                return false;
            }
        }
        return true;
    }

}
