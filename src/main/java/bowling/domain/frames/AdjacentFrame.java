package bowling.domain.frames;

import bowling.domain.Pitching;

import java.util.Optional;

public class AdjacentFrame {
    private final Frame previousFrame;
    private final Frame nextFrame;

    private AdjacentFrame(Frame previousFrame, Frame nextFrame) {
        this.previousFrame = previousFrame;
        this.nextFrame = nextFrame;
    }

    public static AdjacentFrame of(Frame previousFrame, Frame nextFrame) {
        return new AdjacentFrame(previousFrame, nextFrame);
    }

    public Integer getPreviousTotalScore() {
        Optional<Integer> optionalPreviousFrameTotalScore = previousFrame.getTotalScore();
        return optionalPreviousFrameTotalScore.orElseThrow(IllegalStateException::new);
    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    public Optional<Pitching> getNextPitching() {
        return nextFrame.getFirstPitching();
    }

    public Optional<Pitching> getNextAndNextPitching() {
        return nextFrame.getSecondPitching();
    }
}
