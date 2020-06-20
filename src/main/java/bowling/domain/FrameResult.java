package bowling.domain;

import java.util.List;

public class FrameResult {
    private final List<FrameBowlState> frameBowlStates;

    public FrameResult(List<FrameBowlState> frameBowlStates) {
        this.frameBowlStates = frameBowlStates;
    }
}
