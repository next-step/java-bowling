package bowling.dto;

import java.util.List;

public class FramesDTO {
    List<FrameDTO> frames;

    public FramesDTO(List<FrameDTO> frames) {
        this.frames = frames;
    }

    public List<FrameDTO> frames() {
        return frames;
    }
}
