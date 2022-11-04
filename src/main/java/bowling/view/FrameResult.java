package bowling.view;

import bowling.domain.Frame;
import bowling.domain.state.State;

import java.util.stream.Collectors;

import static bowling.domain.Frame.NO_SCORE;

public class FrameResult {

    public String frameSign(Frame frame) {
        if (frame.isLastFrame()) {
            String states = String.format(frame.getStates()
                    .stream()
                    .map(State::describe)
                    .collect(Collectors.joining("|")), "%5s");
            return String.format("%5s ", states);
        }

        State state = frame.getState();
        return String.format(" %3s  ", state.describe());
    }

    public int frameScore(Frame frame) {
        try {
            return frame.getIntScore();
        } catch (UnsupportedOperationException e) {
            return NO_SCORE;
        }
    }
}
