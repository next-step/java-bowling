package bowling.domain.score;

import bowling.domain.frame.Frame;

public class NormalScore extends AbstractScore {

    public NormalScore(int score) {
        super(score);
    }

    public NormalScore(Frame frame) {
        super(frame);
    }

    public static NormalScore valueOf(int score) {
        return new NormalScore(score);
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
