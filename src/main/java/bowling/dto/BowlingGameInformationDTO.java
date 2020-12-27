package bowling.dto;

import java.util.List;

public class BowlingGameInformationDTO {

    private final List<String> frameContents;
    private final int currentFrame;

    public BowlingGameInformationDTO(List<String> frameContents, int currentFrame) {
        this.frameContents = frameContents;
        this.currentFrame = currentFrame;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public List<String> getFrameContents() {
        return frameContents;
    }
}
