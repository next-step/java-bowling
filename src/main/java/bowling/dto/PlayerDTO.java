package bowling.dto;

public class PlayerDTO {
    private String name;
    private FramesDTO frames;

    public PlayerDTO(String name, FramesDTO frames) {
        this.name = name;
        this.frames = frames;
    }

    public String name() {
        return name;
    }

    public FramesDTO framesDTO() {
        return frames;
    }
}
