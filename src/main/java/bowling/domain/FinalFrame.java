package bowling.domain;

import static bowling.domain.PitchFactory.*;

public class FinalFrame extends Frame {
    private static final int MAX_PITCH_COUNT = 3;
    
    public FinalFrame() {
        super(Frames.FRAME_COUNT);
    }

    @Override
    protected boolean isEnd(final int pitchNumber, final int remainPinCount) {
        if (pitchNumber == MAX_PITCH_COUNT) {
            return remainPinCount > 0;
        }
        return pitchNumber > MAX_PITCH_COUNT;
    }

    @Override
    protected int getRemainPinCount(final int pinCount, final Pitch pitch) {
        int remainPinCount = pinCount - pitch.getPinCount();
        if (remainPinCount < 1) {
            return PIN_COUNT;
        }
        return remainPinCount;
    }
}
