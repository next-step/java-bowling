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
            Score normalScore = nextFrame.isLast() ?
                    new LastSpareNormalScore(nextFrame) :
                    new SpareNormalScore(nextFrame);
            return score + normalScore.getScore();
        }
        return score + nextFrame.getStrikeBonus();
    }

    @Override
    public boolean checkValid() {
        NormalFrame nextFrame = (NormalFrame) normalFrame.getNextFrame();
        if (nextFrame.isStrike()) {
            Score normalScore = nextFrame.isLast() ?
                    new LastSpareNormalScore(nextFrame) :
                    new SpareNormalScore(nextFrame);
            return normalScore.checkValid();
        }
        return normalFrame.isCompleted() && nextFrame.checkStrikeBonus();
    }

}
