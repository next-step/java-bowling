package bowling.domain.score;

import bowling.domain.frame.Frame;

public class SpareScore extends AbstractScore {

    public SpareScore(Frame frame) {
        super(frame);
    }

    @Override
    protected int calculateScore() {
        int normalScore = new NormalScore(frame).getScore();
        int bonusScore = frame.getNextFrame().getFirstNumberOfPin();
        return normalScore + bonusScore;
    }

    @Override
    protected boolean checkValid() {
        return frame.isCompleted() && !frame.getNextFrame().isNone();
    }

}
