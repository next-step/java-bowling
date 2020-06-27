package bowling.domain.frame;

import bowling.domain.bonus.BonusScore;
import bowling.domain.bonus.BonusScores;
import bowling.domain.score.Scores;

public class NormalFrame extends Frame {
    private final static int MAX_SCORE = 10;

    public NormalFrame(BonusScores bonusScores) {
        super(new Scores(), bonusScores);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(new BonusScores());
    }

    public Frame createNextFrame(int frameIndex) {
        createBonusScores(frameIndex);
        return new NormalFrame(bonusScores.findAvailableAddBonusScores());
    }

    public Frame createLastFrame(int frameIndex) {
        createBonusScores(frameIndex);
        return new FinalFrame(bonusScores.findAvailableAddBonusScores());
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
    public boolean availablePlay() {
        if (scores.isEmpty()) {
            return true;
        }

        if (scores.isStrikeOrSpare()) {
            return false;
        }

        return scores.firstScore();
    }

    @Override
    public boolean isAvailableCalculatePoint(int frameIndex) {
        if (availablePlay()) {
            return false;
        }

        if (scores.isStrikeOrSpare() && !bonusScores.isEmptyByFrameIndex(frameIndex)) {
            return false;
        }

        if (bonusScores.findBonusScores(frameIndex).isAvailableAddBonusPoint()) {
            return false;
        }

        return true;
    }

}
