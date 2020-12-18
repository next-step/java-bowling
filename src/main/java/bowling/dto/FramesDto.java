package bowling.dto;

import java.util.List;

public class FramesDto {
    private final List<FrameDto> value;

    private FramesDto(List<FrameDto> value) {
        this.value = value;
    }

    public static FramesDto of(List<FrameDto> value) {
        return new FramesDto(value);
    }

    public FrameDto get(int frameNo) {
        if (frameNo <= value.size()) {
            int index = frameNo - 1;
            return value.get(index);
        }

        return null;
    }
}
