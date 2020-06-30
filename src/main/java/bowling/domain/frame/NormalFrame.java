package bowling.domain.frame;

import bowling.domain.bonus.BonusScore;
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
        createBonusScores(frameIndex);
        return new NormalFrame(bonusScores.findAvailableAddBonusScores(), frameIndex);
    }

    public Frame createLastFrame() {
        createBonusScores(frameIndex);
        return new FinalFrame(bonusScores.findAvailableAddBonusScores(), frameIndex);
    }

    private void createBonusScores(int frameIndex) {
        if (scores.isStrike()) {
            bonusScores.addBonusScore(BonusScore.strikeBonusScore(frameIndex));
        }

        if (scores.isSpare()) {
            bonusScores.addBonusScore(BonusScore.spareBonusScore(frameIndex));
        }
    }

    @Override
    public void validateScores(int point) {
        if (scores.totalScore() + point > MAX_SCORE) {
            throw new IllegalArgumentException("score less than 10");
        }
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
