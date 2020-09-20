package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class StrikeNormalScore extends AbstractNormalScore {

    public StrikeNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        return 0;
    }

    @Override
    protected boolean checkValid() {
        return true;
    }

}
