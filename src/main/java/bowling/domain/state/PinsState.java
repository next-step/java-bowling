package bowling.domain.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PinsState {

    private List<Integer> downPins;
    private List<ScoreType> scoreTypes;

    public PinsState(List<Integer> downPins, List<ScoreType> scoreTypes) {
        this.downPins = downPins;
        this.scoreTypes = scoreTypes;
    }

    public List<Integer> getDownPins() {
        return new ArrayList<>(this.downPins);
    }

    public List<ScoreType> getScoreTypes() {
        return new ArrayList<>(this.scoreTypes);
    }

    public boolean hasStrike() {
        return this.scoreTypes.contains(ScoreType.STRIKE);
    }

    public boolean hasSpare() {
        return this.scoreTypes.contains(ScoreType.SPARE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PinsState pinsState = (PinsState) o;
        return Objects.equals(downPins, pinsState.downPins) &&
            Objects.equals(scoreTypes, pinsState.scoreTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(downPins, scoreTypes);
    }
}
