package bowling.domain.score;

import bowling.domain.frame.NormalFrame;

public class StrikeNormalScore extends AbstractNormalScore {

    public StrikeNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        NormalFrame nextFrame = (NormalFrame) normalFrame.getNextFrame();
        int score = new DefaultNormalScore(normalFrame).getScore();
        if (nextFrame.isStrike()) {
            Score spareNormalScore = getSpareNormalScore(nextFrame);
            return score + spareNormalScore.getScore();
        }
        return score + nextFrame.getStrikeBonus();
    }

    @Override
    protected boolean checkValid() {
        NormalFrame nextFrame = (NormalFrame) normalFrame.getNextFrame();
        if (nextFrame.isStrike()) {
            Score spareNormalScore = getSpareNormalScore(nextFrame);
            return spareNormalScore.isValid();
        }
        return normalFrame.isCompleted() && nextFrame.checkStrikeBonus();
    }

    private Score getSpareNormalScore(NormalFrame nextFrame) {
        return nextFrame.isLast() ?
                new LastSpareNormalScore(nextFrame) :
                new SpareNormalScore(nextFrame);
    }

}
