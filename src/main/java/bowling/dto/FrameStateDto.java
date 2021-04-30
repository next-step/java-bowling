package bowling.dto;

import bowling.domain.engine.frame.Frame;

public class FrameStateDto {

    private final String state;

    private FrameStateDto(String state) {
        this.state = state;
    }

    public static FrameStateDto of(Frame frame) {
        return new FrameStateDto(frame.exportState());
    }

    public String getState() {
        return state;
    }

}
