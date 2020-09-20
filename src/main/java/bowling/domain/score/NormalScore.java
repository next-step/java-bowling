package bowling.domain.score;

import bowling.domain.frame.Frame;

public class NormalScore extends AbstractScore {

    public NormalScore(Frame frame) {
        super(frame);
    }

    @Override
    protected int calculateScore() {
        return frame.getTotalNumberOfPin();
    }

    @Override
    protected boolean checkValid() {
        return frame.isCompleted();
    }

}
