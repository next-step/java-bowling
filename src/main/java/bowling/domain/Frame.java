package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.domain.NormalFrame.NORMAL_FRAME_MAXIMUM_INDEX;

public abstract class Frame {
    private final int index;
    protected final PitchingResults pitchingResults;
    private Frame nextFrame;

    protected Frame(final int index) {
        this.index = index;
        this.pitchingResults = PitchingResults.of();
    }

    public static Frame createFirst() {
        return NormalFrame.of(0);
    }

    public Frame createNext() {
        if (isBeforeFinal()) {
            nextFrame = FinalFrame.of(index + 1);
            return nextFrame;
        }

        nextFrame = NormalFrame.of(index + 1);
        return nextFrame;
    }

    private boolean isBeforeFinal() {
        return index == NORMAL_FRAME_MAXIMUM_INDEX;
    }

    public abstract void pitch(Pins pins);

    public boolean isNotPlayable() {
        return !isPlayable();
    }

    public abstract boolean isPlayable();

    public int getIndex() {
        return index;
    }

    public Frame next() {
        return nextFrame;
    }

    public abstract boolean isFinishedAll();

    public List<Integer> getAllFallenPin() {
        return pitchingResults.getAllFallenPin();
    }

    public List<Frame> toFrames() {
        if (isNotFirstFrame()) {
            throw new IllegalStateException();
        }
        final List<Frame> frames = new ArrayList<>(10);
        Frame frame = this;
        frames.add(frame);
        while (frame.next() != null) {
            frame = frame.next();
            frames.add(frame);
        }
        return Collections.unmodifiableList(frames);
    }

    private boolean isNotFirstFrame() {
        return this.index != 0;
    }

}