package bowling.refactoring.domain.state;

import bowling.refactoring.domain.frame.*;

public class Strike extends Finished {
    private static final int STRIKE_ADDITIONAL_SCORE = 2;
    private static final int STRIKE_SCORE = 10;
    private int totalScore;
    private int additionalScore;

    public Strike() {
        this.totalScore = STRIKE_SCORE;
        this.additionalScore = STRIKE_ADDITIONAL_SCORE;
    }

    @Override
    public boolean isEndedCalculateScore() {
        return additionalScore == 0;
    }


    @Override
    public void calculateBonusScore(Frame nextFrame) {

        if (!(nextFrame instanceof FinalFrame) && !nextFrame.state().isEnd()) {
            return;
        }

        if (nextFrame.state() instanceof Strike) {
            this.totalScore += STRIKE_SCORE;
            this.additionalScore--;
            return;
        }

        if ((nextFrame instanceof FinalFrame) && nextFrame.score().pinsList().get(nextFrame.score().pinsList().size()-1).count() == 10) {
            this.totalScore += STRIKE_SCORE;
            this.additionalScore--;
            return;
        }

        if ((nextFrame instanceof FinalFrame) && nextFrame.score().pinsList().size() < 2) {
            return;
        }

        this.totalScore += (nextFrame.score().firstScore().count() + nextFrame.score().secondScore().count());
        this.additionalScore = 0;
    }

    public int totalScore() {
        return this.totalScore;
    }
}