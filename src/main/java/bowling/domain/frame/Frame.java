package bowling.domain.frame;

import bowling.domain.bonus.BonusScore;
import bowling.domain.bonus.BonusScores;
import bowling.domain.dto.ScoreResultDto;
import bowling.domain.score.Scores;

import java.util.List;

public abstract class Frame {
    protected Scores scores;
    protected BonusScores bonusScores;
    protected final int frameIndex;

    protected Frame(Scores scores, BonusScores bonusScores, int frameIndex) {
        this.scores = scores;
        this.bonusScores = bonusScores;
        this.frameIndex = frameIndex;
    }

    public void addPoint(int point) {
        validateScores(point);
        scores.addScore(point);
        bonusScores.addBonusPoint(point);
    }

    public int totalScore() {
        BonusScore bonusScore = bonusScores.findBonusScores(frameIndex);
        return scores.totalScore() + bonusScore.calculateBonusPoints();
    }

    public List<ScoreResultDto> getScoreResultDtos() {
        return scores.convertSoreResultDtos();
    }

    public abstract void validateScores(int point);

    public abstract boolean isAvailablePlay();

    public abstract boolean isAvailableCalculatePoint();
}
