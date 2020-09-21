package bowling.domain;

import java.util.List;

import bowling.domain.frame.Frames;
import bowling.ui.result.DisplayRolledResult;

final class Rolling {
    private final Frames frames;

    Rolling() {
        frames = Frames.of();
    }

    void roll(int fallenPins){
        frames.saveRolledResultAndShouldNextFrame(fallenPins);
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
