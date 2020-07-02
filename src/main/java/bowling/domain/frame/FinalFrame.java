package bowling.domain.frame;

import bowling.domain.bonus.BonusScores;
import bowling.domain.score.Scores;

public class FinalFrame extends Frame {

    public FinalFrame(BonusScores bonusScores, int frameIndex) {
        super(new Scores(), bonusScores, frameIndex);
    }

    @Override
    public void addPoint(int point) {
        if (scores.isStrikeOrSpare()) {
            bonusScores.addBonusScore(scores, frameIndex);
        }
        scores.addScore(point);
        bonusScores.addBonusPoint(point);
    }

    @Override
    public boolean isAvailablePlay() {
        return scores.isLastFrameAvailableAdd();
    }

    @Override
    public boolean isAvailableCalculatePoint() {
        return !isAvailablePlay();
    }
}
