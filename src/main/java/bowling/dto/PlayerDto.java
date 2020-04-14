package bowling.dto;

import bowling.domain.NormalFrame;

import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<NormalFrame> frames;

    public PlayerDto(String name, List<NormalFrame> frames) {
        this.name = name;
        this.frames = frames;
    }

    public String getName() {
        return name;
    }

    public List<NormalFrame> getFrames() {
        return frames;
    }
}
