package bowling.domain;

import java.util.List;

import bowling.domain.core.RolledResult;
import bowling.domain.core.state.RolledResultFactory;
import bowling.domain.frame.Frames;
import bowling.ui.result.DisplayRolledResult;

final class Rolling {
    private final Frames frames;

    Rolling() {
        RolledResultFactory.init();
        frames = Frames.of();
    }

    void roll(int fallenPins){
        RolledResult rolledResult = RolledResultFactory.collectPins(fallenPins)
            .toRolledResult();
        frames.saveRolledResultAndShouldNextFrame(rolledResult);
    }

    List<DisplayRolledResult> framesByRolledResults() {
        return frames.toRolledResults();
    }

    boolean isPlayable(){
        return frames.isNotCompleteFrames();
    }

    boolean hasNextFrameIndex(int currentFrameIndex){
        return frames.hasNextFrameIndex(currentFrameIndex);
    }

    int getTotalFrameScore(){
        return frames.getTotalScore();
    }
}
