package bowling.dto;

import bowling.domain.FrameEnum;

public class FrameDto {
    private final FrameEnum frameEnum;
    private final int startIndex;

    public FrameDto(int startIndex, FrameEnum frameEnum) {
        this.startIndex = startIndex;
        this.frameEnum = frameEnum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public FrameEnum getFrameEnum() {
        return frameEnum;
    }
}
