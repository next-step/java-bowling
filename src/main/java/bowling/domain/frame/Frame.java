package bowling.domain.frame;

import bowling.domain.dto.ScoreResultDto;
import bowling.domain.score.Scores;

import java.util.List;

public abstract class Frame {
    protected Scores scores;

    protected Frame(Scores scores) {
        this.scores = scores;
    }

    public void addPoint(int point) {
        validateScores();
        scores.addScore(point);
    }

    public int totalScore() {
        return scores.totalScore();
    }

    public List<ScoreResultDto> getScoreResultDtos() {
        return scores.convertSoreResultDtos();
    }

    public abstract void validateScores();

    public abstract boolean availablePlay();
}
