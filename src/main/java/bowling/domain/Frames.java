package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.controller.BowlingGame.FIRST_FRAME;
import static bowling.controller.BowlingGame.LAST_FRAME;

public class Frames {
    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
    }

    public int frameNumber() {
        return frames.size();
    }

    public boolean finished() {
        return frames.size() == LAST_FRAME && !currentFrame().continuable();
    }

    public void pitch(int pinNumber) {
        Frame currentFrame = currentFrame();
        Frame nextFrame = currentFrame.pitch(pinNumber);

        if (currentFrame != nextFrame) {
            frames.add(nextFrame);
        }
    }

    private Frame currentFrame() {
        if (frames.size() < FIRST_FRAME) {
            frames.add(new Frame(Sequential.NONE));
        }

        return frames.get(currentIndex());
    }

    private int currentIndex() {
        return frames.size()-1;
    }

    public List<PinNumbers> pinNumbersPerFrame() {
        List<PinNumbers> entirePinNumbers = new ArrayList<>();
        frames.forEach(frame -> entirePinNumbers.add(frame.getPinNumbers()));

        return entirePinNumbers;
    }
}
