package bowling.domain;

import java.util.List;
import java.util.stream.Stream;

import bowling.domain.core.RolledResult;

import static java.util.stream.Collectors.toList;

final class Frames {
    static final int MAX_FRAMES_SIZE = 10;
    private static final int MAX_FOUNDATION_FRAME_SIZE = 9;
    private final List<Frame> frames;
    private int zeroBaseCurrentFrameIndex;

    Frames() {
        frames = Stream.generate(FoundationFrame::new)
                       .limit(MAX_FOUNDATION_FRAME_SIZE)
                       .collect(java.util.stream.Collectors.toList());
        frames.add(new TerminateFrame());
        zeroBaseCurrentFrameIndex = 0;
    }

    void saveRolledResultAndShouldNextFrame(RolledResult rolledResult){
        updateCurrentFrameByRolledResult(rolledResult);
        shouldNextFrame();
    }

    boolean isCompleteFrames(){
        return MAX_FRAMES_SIZE <= currentFrameIndex();
    }

    boolean isNotCompleteFrames(){
        return !isCompleteFrames();
    }

    int currentFrameIndex(){
        return zeroBaseCurrentFrameIndex;
    }

    List<String> toRolledResults() {
        return frames.stream()
                     .map(Frame::toRolledResult)
                     .collect(toList());
    }

    private void updateCurrentFrameByRolledResult(RolledResult rolledResult) {
        if (isNotCompleteFrames()) {
            Frame currentFrame = currentFrame();
            currentFrame.updateRolledResult(rolledResult);
            frames.set(currentFrameIndex(), currentFrame);
        }
    }

    private void shouldNextFrame(){
        if (isNotCompleteFrames()) {
            zeroBaseCurrentFrameIndex += currentFrame().increaseNextStepFrame();
        }
    }

    private Frame currentFrame(){
        return frames.get(currentFrameIndex());
    }
}
