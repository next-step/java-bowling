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

    public void pitch(int pins) {
        if (frameNumber() < FIRST_FRAME) {
            frames.add(new NormalFrame());
        }

        addAdditionalPins(pins);

        Frame currentFrame = currentFrame();
        Frame nextFrame = currentFrame.frameAfterPitch(pins);

        if (currentFrame == nextFrame || finished()) {
            return;
        }

        if (frames.size() == NINTH_FRAME) {
            nextFrame = new FinalFrame();
        }

        frames.add(nextFrame);
    }

    private void addAdditionalPins(int pins) {
        frames.subList(0, currentIndex())
                .stream()
                .filter(Frame::remainderLeft)
                .forEach(frame -> frame.addPins(pins));
    }

    private Frame currentFrame() {
        return frames.get(currentIndex());
    }

    private int currentIndex() {
        return frames.size()-1;
    }

//    public List<Pitches> pinNumbersPerFrame() {
//        List<Pitches> entirePinNumbers = new ArrayList<>();
//        frames.forEach(frame -> entirePinNumbers.add(frame.getPinNumbers()));
//
//        return entirePinNumbers;
//    }
}
