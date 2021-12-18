package bowling.service.dto;

import bowling.domain.result.FrameResult;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameResultDto {
    private final List<State> states;
    private final int score;

    private FrameResultDto(List<State> states, int score) {
        this.states = new ArrayList<>(states);
        this.score = score;
    }

    public static FrameResultDto of(FrameResult result) {
        int score = result.getScore().getValue();
        return new FrameResultDto(result.getStates(), score);
    }

    public List<State> getStates() {
        return Collections.unmodifiableList(states);
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "FrameResultDto{" +
                "states=" + states +
                ", score=" + score +
                '}';
    }
}
