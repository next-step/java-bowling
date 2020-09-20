package bowling.domain.score;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class LastSpareNormalScore extends AbstractNormalScore {

    public LastSpareNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        FinalFrame finalFrame = (FinalFrame) normalFrame.getNextFrame();
        int normalScore = new DefaultNormalScore(normalFrame).getScore();
        int bonusScore = finalFrame.getSpareBonus();
        return normalScore + bonusScore;
    }

    @Override
    protected boolean checkValid() {
        FinalFrame finalFrame = (FinalFrame) normalFrame.getNextFrame();
        return normalFrame.isCompleted() && finalFrame.checkSpareBonus();
    }

}
