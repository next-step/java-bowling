package bowling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Frames {
    private final LinkedList<Frame> frames;

    public Frames(int size) {
        this.frames = new LinkedList<>();
        for (int i = 0; i < size - 1; i++) {
            this.frames.add(new Frame());
        }
        this.frames.add(new FinalFrame());
    }

    public FrameNumber pitching(FrameNumber frameNumber, int hitCount) {
        Frame frame = frames.get(frameNumber.retrieveIndexNumber());
        BowilingTerm bowilingTerm = frame.hitBowlingPin(hitCount);

        if (!bowilingTerm.equals(BowilingTerm.STRIKE)) {
            ListIterator<Frame> frameListIterator = frames.listIterator(frameNumber.retrieveIndexNumber());
            while (frameListIterator.hasPrevious()) {
                Frame previous = frameListIterator.previous();
                previous.calculateBonus(hitCount);
            }
        }

        if (frame.finishFrame()) {
            return frameNumber.next();
        }
        return frameNumber;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
