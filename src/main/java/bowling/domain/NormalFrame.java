package bowling.domain;

public class NormalFrame extends Frame {
    private static final int MAX_PITCH_COUNT = 2;
    
    public NormalFrame(final int frameNumber) {
        super(frameNumber);
    }
    
    @Override
    protected boolean isEnd(final int pitchNumber, final int remainPinCount) {
        return pitchNumber > MAX_PITCH_COUNT || remainPinCount < 1;
    }

    @Override
    protected int getRemainPinCount(final int pinCount, final Pitch pitch) {
        return pinCount - pitch.getPinCount();
    }
}
