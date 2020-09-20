package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.roll.Roll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static bowling.domain.BowlingProperty.NORMAL_FRAME_START_NUMBER;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();
    private Frame currFrame;

    public Frames() {
        currFrame = new NormalFrame(NORMAL_FRAME_START_NUMBER);
        frames.add(currFrame);
    }

    public Iterator<String> markingIterator() {
        return frames.stream()
                .map(Frame::getMarking)
                .iterator();
    }

    public boolean isComplete() {
        return currFrame.isFinalFrame() && currFrame.isComplete();
    }

    public void addRoll(Roll roll) {
        currFrame.addRoll(roll);
    }

    public void endCurrentFrame() {
        currFrame.setComplete();
    }

    public void nextFrame() {
        if (!currFrame.isFinalFrame()) {
            currFrame = currFrame.nextFrame();
            frames.add(currFrame);
        }
    }

    public boolean cannotRollMore(Pins pins) {
        return currFrame.cannotRollMore(pins);
    }

    public int getCurrentFrameNumber() {
        return currFrame.getNumber();
    }
}
