package bowling.domain.frame;

import bowling.domain.bonus.BonusScores;
import bowling.domain.score.Scores;

public class NormalFrame extends Frame {
    private final static int MAX_SCORE = 10;
    private final static int FIRST_FRAME_INDEX = 0;

    public NormalFrame(BonusScores bonusScores, int frameIndex) {
        super(new Scores(), bonusScores, frameIndex);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(new BonusScores(), FIRST_FRAME_INDEX);
    }

    public Frame createNextFrame() {
        bonusScores.addBonusScore(scores, frameIndex);
        return new NormalFrame(bonusScores.findAvailableAddBonusScores(), frameIndex + 1);
    }

    public Frame createLastFrame() {
        bonusScores.addBonusScore(scores, frameIndex);
        return new FinalFrame(bonusScores.findAvailableAddBonusScores(), frameIndex + 1);
    }

    @Override
    public boolean isAvailablePlay() {
        return scores.isAvailableAdd();
    }

    @Override
    public boolean isAvailableCalculatePoint() {
        if (isAvailablePlay()) {
            return false;
        }

        if (bonusScores.isAvailableAddBonusScores(frameIndex)) {
            return false;
        }

        return true;
    }

}
