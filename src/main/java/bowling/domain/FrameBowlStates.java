package bowling.domain;


import java.util.ArrayList;
import java.util.List;

public class FrameBowlStates {

    private final List<FrameBowlState> frameBowlStates;

    public FrameBowlStates(List<FrameBowlState> frameBowlStates) {
        this.frameBowlStates = new ArrayList<>(frameBowlStates);
    }

    void add(FrameBowlState frameBowlState) {
        this.frameBowlStates.add(frameBowlState);
    }

    boolean isStrike() {
        return this.frameBowlStates.stream()
            .filter(frameBowlState -> frameBowlState.getScoreType() == ScoreType.STRIKE)
            .findFirst()
            .isPresent();
    }

    boolean isSpare() {
        return this.frameBowlStates.stream()
            .filter(frameBowlState -> frameBowlState.getScoreType() == ScoreType.SPARE)
            .findFirst()
            .isPresent();
    }

    public List<FrameBowlState> getFrameBowlStates() {
        return new ArrayList<>(this.frameBowlStates);
    }
}
