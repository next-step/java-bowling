package bowling.domain;

import static bowling.domain.Frames.*;

import java.util.function.Function;

import bowling.domain.count.Gutter;
import bowling.domain.count.Miss;
import bowling.domain.count.Remain;
import bowling.domain.count.Spare;
import bowling.domain.count.Strike;

public final class PitchFactory {
    public static final int PIN_COUNT = 10;
    
    private final Function<Integer, Integer> pinCount;
    
    public PitchFactory(Function<Integer, Integer> pinCount) {
        this.pinCount = pinCount;
    }

    public Pitch create(final int frameNumber, final int pitchNumber, final int remainPinCount) {
        int pinCount = this.pinCount.apply(remainPinCount);
        if (pitchNumber == 1) {
            return createFirstPitch(frameNumber, pinCount);
        }
        if (frameNumber == FRAME_COUNT) {
            return createExtraPitch(pinCount, remainPinCount);
        }
        return createExtraPitch(frameNumber, pinCount, remainPinCount);
    }

    private Pitch createFirstPitch(final int frameNumber, final int pinCount) {
        if (pinCount == PIN_COUNT) {
            return new Strike(frameNumber);
        }
        if (pinCount == 0) {
            return new Gutter(frameNumber);
        }
        return new Remain(frameNumber, pinCount);
    }

    private Pitch createExtraPitch(final int frameNumber, final int pinCount, final int remainPinCount) {
        if (pinCount == PIN_COUNT || pinCount == remainPinCount) {
            return new Spare(frameNumber, pinCount);
        }
        if (pinCount == 0) {
            return new Gutter(frameNumber);
        }
        return new Miss(frameNumber, pinCount);
    }
    
    private Pitch createExtraPitch(final int pinCount, final int remainPinCount) {
        if (pinCount == PIN_COUNT) {
            return new Strike(FRAME_COUNT);
        }
        if (pinCount == remainPinCount) {
            return new Spare(FRAME_COUNT, pinCount);
        }
        if (pinCount == 0) {
            return new Gutter(FRAME_COUNT);
        }
        return new Miss(FRAME_COUNT, pinCount);
    }
}
