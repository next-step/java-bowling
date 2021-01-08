package bowling.dto;

import java.util.Collections;
import java.util.List;

public class BowlingGameResultsDTO {

    private final int currentFrame;
    private final List<String> frames;
    private final List<String> accumulatedScore;

    public BowlingGameResultsDTO(int currentFrame, List<String> frames, List<String> accumulatedScore) {
        this.currentFrame = currentFrame;
        this.frames = frames;
        this.accumulatedScore = accumulatedScore;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public List<String> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public List<String> getAccumulatedScore() {
        return Collections.unmodifiableList(accumulatedScore);
    }
}
