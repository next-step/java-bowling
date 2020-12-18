package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import java.util.List;

public class FrameResult {

    private final List<Integer> downPins;
    private final ScoreType scoreType;


    public FrameResult(List<Integer> downPins, ScoreType scoreType) {
        this.downPins = downPins;
        this.scoreType = scoreType;
    }

    public List<Integer> getDownPins() {
        return downPins;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }
}
