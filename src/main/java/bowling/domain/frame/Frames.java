package bowling.domain.frame;

import bowling.domain.pins.Pins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 10;

    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>();
        frames.add(NormalFrame.of(START_NUMBER));
    }

    public static Frames of() {
        return new Frames();
    }

    public void bowl(Pins pins) {
        Frame frame = getLastFrame();
        if (frame.isFinish()) {
            createFrame();
            return;
        }

        frame.bowl(pins);
    }

    public boolean isFinish() {
        return frames.size() == END_NUMBER && getLastFrame().isFinish();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private void createFrame() {
        if (frames.size() < END_NUMBER - 1) {
            frames.add(NormalFrame.of(getLastFrame().getFrameNumber() + 1));
            return;
        }

        frames.add(FinalFrame.of());
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }
}
