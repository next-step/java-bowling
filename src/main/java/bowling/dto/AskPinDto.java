package bowling.dto;

public class AskPinDto {
    private final String name;
    private final int frameNumber;

    public AskPinDto(String name, int frameNumber) {
        this.name = name;
        this.frameNumber = frameNumber;
    }

    public String getName() {
        return name;
    }

    public int getFrameNumber() {
        return frameNumber;
    }
}
