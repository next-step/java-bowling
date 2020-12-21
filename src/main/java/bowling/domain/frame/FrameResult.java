package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import java.util.List;

public class FrameResult {

    private final List<Integer> downPins;
    private final ScoreType scoreType;
    private final Score score;


    public FrameResult(List<Integer> downPins, ScoreType scoreType, Score score) {
        this.downPins = downPins;
        this.scoreType = scoreType;
        this.score = score;
    }

    public List<Integer> getDownPins() {
        return downPins;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public Score getScore() {
        return score;
    }
}
