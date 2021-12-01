package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.domain.frame.Frame;
import bowling.utils.Preconditions;

import java.util.List;

public class BowlingClub {
    private int currentIndex;
    private final List<Frame> frames;

    private BowlingClub(List<Frame> frames) {
        Preconditions.checkEmpty(frames, "frames은 필수값입니다.");

        this.frames = frames;
    }

    public static BowlingClub from(List<Frame> frames) {
        return new BowlingClub(frames);
    }

    public void knockedDown(Pins pins) {
        Frame frame = getCurrentFrame();
        frame.knockedDown(pins);

        if (frame.isFrameOver()) {
            currentIndex++;
        }
    }

    private Frame getCurrentFrame() {
        return frames.get(currentIndex);
    }

    public boolean isGameOver() {
        return getCurrentFrame().isFinalFrameOver();
    }

    @GetterForUI
    public FrameNumber getCurrentFrameNumber() {
        return getCurrentFrame().getCurrentFrameNumber();
    }

    @GetterForUI
    public FramePins getPins(int frameNumber) {
        return frames.get(frameNumber - 1).getPins();
    }
}
