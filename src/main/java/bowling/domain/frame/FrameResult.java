package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FrameResult {

    private final List<Integer> downPins;
    private final ScoreType scoreType;
    private final Score score;


    public FrameResult(List<Integer> downPins, ScoreType scoreType,
                       Score score) {
        this.downPins = downPins;
        this.score = score;
        this.scoreType = scoreType;
    }

    public List<Integer> getDownPins() {
        return downPins;
    }

    public Score getScore() {
        return score;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }
}
