package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;

public class BowlingClub {
    private final Frame frame;

    private BowlingClub(Frame frame) {
        this.frame = frame;
    }

    public static BowlingClub create(Frame frame) {
        return new BowlingClub(frame);
    }

    public void knockedDown(Pins pins) {
        frame.knockedDown(pins);
    }

    public boolean isGameOver() {
        return frame.isGameOver();
    }

    public FrameNumber getCurrentFrameNumber() {
        return frame.getCurrentFrameNumber();
    }

    public FramePins getPins(FrameNumber frameNumber) {
        return frame.getPins(frameNumber);
    }
}
