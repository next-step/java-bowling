package bowling.domain.score;

import bowling.domain.frame.NormalFrame;

public class SpareNormalScore extends AbstractNormalScore {

    public SpareNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        int normalScore = new DefaultNormalScore(normalFrame).getScore();
        int bonusScore = normalFrame.getNextFrame().getFirstNumberOfPin();
        return normalScore + bonusScore;
    }

    @Override
    protected boolean checkValid() {
        return normalFrame.isCompleted() && !normalFrame.getNextFrame().isNone();
    }

}
