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
        int score = new DefaultNormalScore(normalFrame).getScore();
        int bonusScore = nextFrame.getSpareBonus();
        return score + bonusScore;
    }

    @Override
    public boolean checkValid() {
        FinalFrame nextFrame = (FinalFrame) normalFrame.getNextFrame();
        return normalFrame.isCompleted() && nextFrame.checkSpareBonus();
    }

}
