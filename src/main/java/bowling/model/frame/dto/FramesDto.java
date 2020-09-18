package bowling.model.frame.dto;

import java.util.Collections;
import java.util.List;

public class FramesDto {
    private final List<FrameDto> frames;

    public FramesDto(List<FrameDto> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public List<FrameDto> getFrames() {
        return frames;
    }
}
