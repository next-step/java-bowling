package bowling.domain.score;

import bowling.domain.frame.NormalFrame;

public class DefaultNormalScore extends AbstractNormalScore {

    public DefaultNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        return normalFrame.getTotalNumberOfPin();
    }

    @Override
    public boolean checkValid() {
        return normalFrame.isCompleted();
    }

}
