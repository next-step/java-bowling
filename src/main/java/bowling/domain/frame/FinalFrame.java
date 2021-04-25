package bowling.domain.frame;

import bowling.exception.NoNextFrameException;

public final class FinalFrame extends Frame {

    private FinalFrame(RoundNumber roundNumber, FrameScore frameScore) {
        super(roundNumber, frameScore);
    }

    public static FinalFrame from(FrameScore frameScore) {
        return new FinalFrame(RoundNumber.MAX_ROUND_NUMBER, frameScore);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public Frame createNextFrame() {
        throw new NoNextFrameException();
    }

    @Override
    public void knockDownPin(Pin pin) {

    }
}
