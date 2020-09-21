package bowling.domain.score;

import bowling.domain.frame.FinalFrame;

public class DefaultFinalScore extends AbstractFinalScore {

    public DefaultFinalScore(FinalFrame finalFrame) {
        super(finalFrame);
    }

    @Override
    protected int calculateScore() {
        return finalFrame.getTotalNumberOfPin();
    }

    @Override
    protected boolean checkValid() {
        return finalFrame.isEnd();
    }

}
