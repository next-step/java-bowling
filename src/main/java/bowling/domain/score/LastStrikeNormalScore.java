package bowling.domain.score;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;

public class LastStrikeNormalScore extends AbstractNormalScore {

    public LastStrikeNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        FinalFrame nextFrame = (FinalFrame) normalFrame.getNextFrame();
        int score = new DefaultNormalScore(normalFrame).getScore();
        int bonusScore = nextFrame.getStrikeBonus();
        return score + bonusScore;
    }

    @Override
    protected boolean checkValid() {
        FinalFrame nextFrame = (FinalFrame) normalFrame.getNextFrame();
        return normalFrame.isCompleted() && nextFrame.checkStrikeBonus();
    }

}
