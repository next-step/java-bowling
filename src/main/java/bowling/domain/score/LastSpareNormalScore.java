package bowling.domain.score;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;

public class LastSpareNormalScore extends AbstractNormalScore {

    public LastSpareNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        FinalFrame nextFrame = (FinalFrame) normalFrame.getNextFrame();
        int normalScore = new DefaultNormalScore(normalFrame).getScore();
        int bonusScore = nextFrame.getSpareBonus();
        return normalScore + bonusScore;
    }

    @Override
    protected boolean checkValid() {
        FinalFrame nextFrame = (FinalFrame) normalFrame.getNextFrame();
        return normalFrame.isCompleted() && nextFrame.checkSpareBonus();
    }

}
