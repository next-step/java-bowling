package bowling.domain;

import java.util.List;

import bowling.domain.core.RolledResult;
import bowling.domain.core.RolledResultFactory;

final class Rolling {
    private final Frames frames;

    Rolling() {
        frames = new Frames();
    }

    void roll(int fallenPins){
        RolledResult rolledResult = RolledResultFactory.collectPins(fallenPins)
            .toRolledResult();
        frames.saveRolledResultAndShouldNextFrame(rolledResult);
    }

    List<String> framesByRolledResults() {
        return frames.toRolledResults();
    }

    boolean isPlayable(){
        return frames.isNotCompleteFrames();
    }

    boolean hasNextFrameIndex(int currentFrameIndex){
        return frames.currentFrameIndex() <= currentFrameIndex;
    }
}
