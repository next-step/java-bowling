package bowling.domain.dto;

import bowling.domain.frame.Frame;

public class FrameResponse {
    private final Frame frame;

    public FrameResponse(Frame frame) {
        this.frame = frame;
    }

    public Frame getFrame() {
        return frame;
    }

    @Override
    public String toString() {
        return "FrameResponse{" +
                ", frame=" + frame +
                '}';
    }
}
