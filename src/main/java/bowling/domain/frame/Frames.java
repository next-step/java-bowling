package bowling.domain.frame;

import java.util.List;
import java.util.stream.Stream;

import bowling.domain.core.RolledResult;
import bowling.ui.result.DisplayRolledResult;

import static bowling.domain.core.state.NotAtRolledResult.notAtRolledResult;
import static java.util.stream.Collectors.toList;

public final class Frames {
    public static final int MAX_FRAMES_SIZE = 10;
    static final int MAX_FOUNDATION_FRAME_SIZE = 9;
    private final List<Frame> frames;
    private int zeroBaseCurrentFrameIndex;

    Frames() {
        frames = Stream.generate(FoundationFrame::new)
                       .limit(MAX_FOUNDATION_FRAME_SIZE)
                       .collect(toList());
        frames.add(new TerminateFrame());
        zeroBaseCurrentFrameIndex = 0;
    }

    public static Frames of() {
        return new Frames();
    }

    public void saveRolledResultAndShouldNextFrame(RolledResult rolledResult){
        updateCurrentFrameByRolledResult(rolledResult);
        updatePreviousFrameByScore(rolledResult);
        shouldNextFrame();
        updateTerminalFrameByScore();
    }

    private void updateCurrentFrameByRolledResult(RolledResult rolledResult) {
        if (isNotCompleteFrames()) {
            currentFrame().updateRolledResult(rolledResult);
        }
    }

    private void updatePreviousFrameByScore(RolledResult rolledResult) {
        if (hasPreviousFrameIndex() && isNotCompleteFrames()){
            previousFrame().updateScore(rolledResult);
        }
    }

    private boolean hasPreviousFrameIndex() {
        return 0 < currentFrameIndex();
    }

    private void shouldNextFrame(){
        if (isNotCompleteFrames()) {
            zeroBaseCurrentFrameIndex += currentFrame().increaseNextStepFrame();
        }
    }

    private void updateTerminalFrameByScore(){
        if (isCompleteFrames()) {
            frames.get(MAX_FOUNDATION_FRAME_SIZE)
                  .updateScore(notAtRolledResult());
        }
    }

    boolean isCompleteFrames(){
        return MAX_FRAMES_SIZE <= currentFrameIndex();
    }

    public boolean isNotCompleteFrames(){
        return !isCompleteFrames();
    }

    public int currentFrameIndex(){
        return zeroBaseCurrentFrameIndex;
    }

    public int getTotalScore(){
        return frames.stream()
                     .mapToInt(Frame::getScore)
                     .sum();
    }

    public List<DisplayRolledResult> toRolledResults() {
        return frames.stream()
                     .map(Frame::toDisplayRolledResult)
                     .collect(toList());
    }

    private Frame previousFrame() {
        return frames.get(currentFrameIndex()-1);
    }

    private Frame currentFrame(){
        return frames.get(currentFrameIndex());
    }

    public boolean hasNextFrameIndex(int currentFrameIndex) {
        return currentFrameIndex() <= currentFrameIndex;
    }
}
