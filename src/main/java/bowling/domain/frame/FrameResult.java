package bowling.domain.frame;

import bowling.domain.score.Score3;
import bowling.domain.score.ScoreType2;

import java.util.List;

public class FrameResult {
    private List<Integer> downPins;
    private ScoreType2 scoreType;
    private Score3 score;

    public FrameResult(List<Integer> downPins, ScoreType2 scoreType, Score3 score) {
        this.downPins = downPins;
        this.scoreType = scoreType;
        this.score = score;
    }

    public List<Integer> getDownPins() {
        return downPins;
    }

    public ScoreType2 getScoreType() {
        return scoreType;
    }

    public Score3 getScore() {
        return score;
    }
}
