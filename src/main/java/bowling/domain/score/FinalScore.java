package bowling.domain.score;

import bowling.domain.frame.FinalFrame;

public class FinalScore extends AbstractFinalScore {

    public FinalScore(FinalFrame finalFrame) {
        super(finalFrame);
    }

    @Override
    protected int calculateScore() {
        return finalFrame.getTotalNumberOfPin();
    }

    @Override
    public boolean checkValid() {
        return finalFrame.isCompleted();
    }

}
