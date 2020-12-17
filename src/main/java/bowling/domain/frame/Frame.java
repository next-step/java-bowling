package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.PitchingResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.domain.frame.NormalFrame.NORMAL_FRAME_MAXIMUM_INDEX;

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
        if (index == NORMAL_FRAME_MAXIMUM_INDEX) {
            nextFrame = FinalFrame.of(index + 1);
            return nextFrame;
        }

        nextFrame = NormalFrame.of(index + 1);
        return nextFrame;
    }

    public List<Frame> toFrames() {
        if (this.index != 0) {
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

    public boolean isNotPlayable() {
        return !isPlayable();
    }

    public int getIndex() {
        return index;
    }

    public Frame next() {
        return nextFrame;
    }

    public abstract void pitch(Pins pins);

    public abstract boolean isPlayable();

    public abstract boolean isFinishedAll();

    public abstract String getSymbol();
}