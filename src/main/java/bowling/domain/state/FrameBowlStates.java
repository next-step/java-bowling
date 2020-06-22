package bowling.domain.state;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FrameBowlStates {

    private final List<FrameBowlState> frameBowlStates;

    public FrameBowlStates(List<FrameBowlState> frameBowlStates) {
        this.frameBowlStates = new ArrayList<>(frameBowlStates);
    }

    public void add(FrameBowlState frameBowlState) {
        this.frameBowlStates.add(frameBowlState);
    }

    public boolean hasStrike() {
        return this.frameBowlStates.stream()
            .anyMatch(frameBowlState -> frameBowlState.getScoreType() == ScoreType.STRIKE);
    }

    public boolean hasSpare() {
        return this.frameBowlStates.stream()
            .anyMatch(frameBowlState -> frameBowlState.getScoreType() == ScoreType.SPARE);
    }

    public List<FrameBowlState> getFrameBowlStates() {
        return new ArrayList<>(this.frameBowlStates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameBowlStates that = (FrameBowlStates) o;
        return Objects.equals(frameBowlStates, that.frameBowlStates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameBowlStates);
    }
}
