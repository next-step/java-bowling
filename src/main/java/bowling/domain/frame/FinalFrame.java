package bowling.domain.frame;

import bowling.domain.pin.Pin;
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
        return true;
    }

    @Override
    public Frame createNextFrame() {
        throw new NoNextFrameException();
    }

    @Override
    public void knockDownPin(Pin pin) {
        frameScore = frameScore.knockDownPin(pin);
    }

    @Override
    public String status() {
        return frameScore.status();
    }
}
