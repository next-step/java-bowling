package bowling.domain.score;

import bowling.domain.frame.NormalFrame;

public class SpareNormalScore extends AbstractNormalScore {

    public SpareNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        NormalFrame nextFrame = (NormalFrame) normalFrame.getNextFrame();
        int score = new DefaultNormalScore(normalFrame).getScore();
        int bonusScore = nextFrame.getSpareBonus();
        return score + bonusScore;
    }

    @Override
    protected boolean checkValid() {
        NormalFrame nextFrame = (NormalFrame) normalFrame.getNextFrame();
        return normalFrame.isCompleted() && nextFrame.checkSpareBonus();
    }

}
