package bowling.dto;

import bowling.domain.frame.FrameStatus;

public class FrameStatusDto {
    private final FrameStatus frameStatus;

    public FrameStatusDto(FrameStatus frameStatus) {
        this.frameStatus = frameStatus;
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }
}
