package bowling.domain.score;

import bowling.domain.frame.Frame;

public class SpareScore extends AbstractScore {

    public SpareScore(Frame frame) {
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
