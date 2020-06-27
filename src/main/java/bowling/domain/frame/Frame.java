package bowling.domain.frame;

import bowling.domain.bonus.BonusScores;
import bowling.domain.dto.ScoreResultDto;
import bowling.domain.score.Scores;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Frame {
    protected Scores scores;
    protected BonusScores bonusScores;

    protected Frame(Scores scores, BonusScores bonusScores) {
        this.scores = scores;
        this.bonusScores = bonusScores;
    }

    public void addPoint(int point) {
        validateScores(point);
        scores.addScore(point);
        bonusScores.addBonusPoint(point);
    }

    public int totalScore() {
        return scores.totalScore();
    }

    public List<ScoreResultDto> getScoreResultDtos() {
        List<ScoreResultDto> scoreResultDtos = scores.convertSoreResultDtos();
        if (Objects.nonNull(bonusScores)) {
            scoreResultDtos = new ArrayList<>(scoreResultDtos);
        }
        return new ArrayList<>(scoreResultDtos);
    }

    public abstract void validateScores(int point);

    public abstract boolean availablePlay();
}
