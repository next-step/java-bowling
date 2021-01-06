package bowling.dto;

import java.util.Collections;
import java.util.List;

public class BowlingGameResultsDTO {

    private final int currentFrame;
    private final List<String> frames;

    public BowlingGameResultsDTO(int currentFrame, List<String> frames) {
        this.currentFrame = currentFrame;
        this.frames = frames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public List<String> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
