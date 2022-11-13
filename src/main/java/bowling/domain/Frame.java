package bowling.domain;

import static bowling.domain.PitchFactory.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Frame {
    private static final PitchFactory PITCH_FACTORY = new PitchFactory(remainPinCount -> {
        return (int) (Math.random() * (remainPinCount - 1) + 1);
    });
    
    private final int number;
    private final List<Pitch> pitches;
    
    protected Frame(final int number) {
        this.number = number;
        pitches = new ArrayList<>();
    }

    public static Frame create(final int frameNumber) {
        if (isLast(frameNumber)) {
            return new FinalFrame().start();
        }
        return new NormalFrame(frameNumber).start();
    }

    public Frame start() {
        int remainPinCount = PIN_COUNT;
        for (int pitchNumber = 1; !isEnd(pitchNumber, remainPinCount); pitchNumber++) {
            Pitch pitch = PITCH_FACTORY.create(number, pitchNumber, remainPinCount);
            pitches.add(pitch);
            remainPinCount = getRemainPinCount(remainPinCount, pitch);
        }
        return this;
    }

    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
    
    protected abstract boolean isEnd(int pitchNumber, int remainPinCount);

    protected abstract int getRemainPinCount(int remainPinCount, Pitch pitch);

    private static boolean isLast(int frameNumber) {
        return frameNumber == Frames.FRAME_COUNT;
    }
}
