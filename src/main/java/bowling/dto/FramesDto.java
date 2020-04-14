package bowling.dto;

import java.util.List;

public class FramesDto {
    private final List<FrameDto> frames;

    public FramesDto(List<FrameDto> frames) {
        this.frames = frames;
    }

    public List<FrameDto> getFrames() {
        return frames;
    }
}
