package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.controller.BowlingGame.*;

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
        if (frameNumber() < FIRST_FRAME) {
            frames.add(new NormalFrame());
        }

        Frame currentFrame = currentFrame();
        Frame nextFrame = currentFrame.pitch(pinNumber);

        if (currentFrame == nextFrame) {
            return;
        }

        if (frames.size() == NINTH_FRAME) {
            nextFrame = new FinalFrame();
        }

        frames.add(nextFrame);
    }

    private Frame currentFrame() {
        return frames.get(currentIndex());
    }

    private int currentIndex() {
        return frames.size()-1;
    }

    private void addPins(int pinNumber) {
        frames.forEach(it -> it.addPins(pinNumber));
    }

//    public List<Pitches> pinNumbersPerFrame() {
//        List<Pitches> entirePinNumbers = new ArrayList<>();
//        frames.forEach(frame -> entirePinNumbers.add(frame.getPinNumbers()));
//
//        return entirePinNumbers;
//    }
}
