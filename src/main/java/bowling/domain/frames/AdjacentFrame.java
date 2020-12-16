package bowling.domain.frames;

import bowling.domain.Pitching;

import java.util.Optional;

public class AdjacentFrame {
    private final FrameImpl previousFrame;
    private final FrameImpl nextFrame;

    private AdjacentFrame(FrameImpl previousFrame, FrameImpl nextFrame) {
        this.previousFrame = previousFrame;
        this.nextFrame = nextFrame;
    }

    public static AdjacentFrame of(FrameImpl previousFrame, FrameImpl nextFrame) {
        return new AdjacentFrame(previousFrame, nextFrame);
    }

    public Integer getPreviousTotalScore() {
        Optional<Integer> optionalPreviousFrameTotalScore = previousFrame.getTotalScore();
        return optionalPreviousFrameTotalScore.orElseThrow(IllegalStateException::new);
    }

    public FrameImpl getNextFrame() {
        return nextFrame;
    }

    public Optional<Pitching> getNextPitching() {
        return nextFrame.getFirstPitching();
    }

    public Optional<Pitching> getNextAndNextPitching() {
        return nextFrame.getSecondPitching();
    }
}
