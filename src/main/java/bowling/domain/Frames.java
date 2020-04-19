package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frames {
    public static final int DEFAULT_FRAME_SIZE = 10;
    private static final int ONE = 1;

    private List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
        initFrames();
    }

    public Frames(Frames frames) {
        this.frames = new ArrayList<>(frames.frames);
    }

    private void initFrames() {
        this.frames.add(new NormalFrame());
    }

    public boolean addPinCount(int pinCount) {
        Frame lastFrame = this.frames.get(getLastIndex());

        boolean result = lastFrame.addPinCount(pinCount);
        if(!isFinished() && lastFrame.isDone()) {
            frames.add(getNewFrame());
        }

        return result;
    }

    protected Frame getNewFrame() {
        int lastIndex = getLastIndex();
        Frame lastFrame = this.frames.get(lastIndex);

        if(isBeforeMax()) {
            return lastFrame.createNext(new FinalFrame());
        }
        return lastFrame.createNext();
    }

    public int size() {
        return frames.size();
    }

    private Frame getLastFrame() {
        return frames.get(lastIndex());
    }

    private int lastIndex() {
        return frames.size() - ONE;
    }

    private boolean isBeforeMax() {
        return frames.size() == (DEFAULT_FRAME_SIZE - ONE);
    }

    public boolean isFinished() {
        Frame lastFrame = getLastFrame();
        return lastFrame.isLast() && lastFrame.isDone();
    }

    public Optional<Integer> getFrameScore(int frameIndex) {
        return frames.get(frameIndex).getScore();
    }

    public List<Pitch> getFramePinCounts(int index) {
        return frames.get(index).getFramePitch();
    }
    private int getLastIndex() {
        return frames.size() - ONE;
    }
}
