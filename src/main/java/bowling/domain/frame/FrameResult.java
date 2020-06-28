package bowling.domain.frame;

import bowling.domain.ScoreType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FrameResult {

    private final List<Integer> downPins;
    private final Optional<ScoreType> scoreType;
    private final Optional<Integer> score;

    public FrameResult(List<Integer> downPins, Optional<ScoreType> scoreType,
        Optional<Integer> score) {
        this.downPins = downPins;
        this.score = score;
        this.scoreType = scoreType;
    }

    public List<Integer> getDownPins() {
        return downPins;
    }

    public Optional<Integer> getScore() {
        return score;
    }

    public Optional<ScoreType> getScoreType() {
        return scoreType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResult that = (FrameResult) o;
        return Objects.equals(downPins, that.downPins) &&
            Objects.equals(scoreType, that.scoreType) &&
            Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(downPins, scoreType, score);
    }
}
