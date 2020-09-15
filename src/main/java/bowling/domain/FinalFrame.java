package bowling.domain;

import bowling.score.FinalScores;
import bowling.score.Scores;

public class FinalFrame extends Frame {

    public FinalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public static FinalFrame of(int frameNumber, Scores scores) {
        return new FinalFrame(frameNumber, scores);
    }


    @Override
    public boolean canBowl() {
        if (scores == null) {
            return true;
        }
        if (scores.isStrike() && !scores.hasBonusScore()) {
            return true;
        }
        if (scores.hasSecondScore() && FinalScores.isSpare(scores)) {
            return true;
        }
        return !scores.hasSecondScore();
    }

    @Override
    public Frame next() {
        int nextFrameNumber = this.frameNumber + 1;

        return FinalFrame.of(nextFrameNumber, FinalScores.init());
    }

}
