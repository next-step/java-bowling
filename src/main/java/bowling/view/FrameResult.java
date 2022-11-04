package bowling.view;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.state.State;

import java.util.stream.Collectors;

public class FrameResult {
    private static final String GUTTER = "-";

    private final Frame frame;

    public FrameResult(Frame frame) {
        this.frame = frame;
    }

    public String getFrameSign() {
        return createFrameSign().replaceAll("0", GUTTER);
    }

    public int getFrameScore() {
        try {
            return frame.getIntScore();
        } catch (UnsupportedOperationException e) {
            return -1;
        }
    }

    private String createFrameSign() {
        if (frame.isLastFrame()) {
            String result = ((FinalFrame) frame).getStates()
                    .stream()
                    .map(State::describe)
                    .collect(Collectors.joining("|"));
            return String.format("%5s ", result);
        }

        State state = frame.getState();
        return String.format(" %3s  ", state.describe());
    }
}
