package bowling.dto;

import java.util.List;

public class FramesDto {

    private final List<String> frames;

    public FramesDto(List<String> frames) {
        this.frames = frames;
    }

    public List<String> getFrames() {
        return frames;
    }
}
