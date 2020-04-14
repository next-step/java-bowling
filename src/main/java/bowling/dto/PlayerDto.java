package bowling.dto;

public class PlayerDto {
    private final String name;
    private final FramesDto frames;

    public PlayerDto(String name, FramesDto framesDto) {
        this.name = name;
        this.frames = framesDto;
    }

    public String getName() {
        return name;
    }

    public FramesDto getFrames() {
        return frames;
    }
}
