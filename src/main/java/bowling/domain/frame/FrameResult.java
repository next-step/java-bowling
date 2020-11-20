package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreData;
import bowling.domain.score.ScoreType;

import java.util.List;

public class FrameResult {
    private List<Integer> downPins;
    private ScoreData scoreData;

    public FrameResult(List<Integer> downPins, ScoreData scoreData) {
        this.downPins = downPins;
        this.scoreData = scoreData;
    }

    public List<Integer> getDownPins() {
        return downPins;
    }

    public ScoreType getScoreType() {
        return scoreData.getScoreType();
    }

    public Score getScore() {
        return scoreData.getScore();
    }
}
