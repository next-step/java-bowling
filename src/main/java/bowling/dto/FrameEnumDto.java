package bowling.dto;

import bowling.domain.frame.FrameEnum;

public class FrameEnumDto {
    private final FrameEnum frameEnum;

    public FrameEnumDto(FrameEnum frameEnum) {
        this.frameEnum = frameEnum;
    }

    public FrameEnum getFrameEnum() {
        return frameEnum;
    }
}
