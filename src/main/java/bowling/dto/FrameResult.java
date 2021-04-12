package bowling.dto;

import bowling.domain.frame.Frame;

public class FrameResult {

    private int frameNumber;

    private String state;

    public FrameResult(Frame frame) {
        this.frameNumber = frame.number().number();
        this.state = frame.currentState().stateInString();
    }

    public int frameNumber() {
        return frameNumber;
    }

    public String state() {
        return state;
    }
}
