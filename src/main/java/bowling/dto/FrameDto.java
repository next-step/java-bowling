package bowling.dto;

import bowling.domain.frame.FrameEnum;

public class FrameDto {
    private final FrameEnum frameEnum;

    public FrameDto(FrameEnum frameEnum) {
        this.frameEnum = frameEnum;
    }

    public FrameEnum getFrameEnum() {
        return frameEnum;
    }
}
