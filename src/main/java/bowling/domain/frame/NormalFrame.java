package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.exception.IllegalNormalFrameException;

public final class NormalFrame extends Frame {

    public static final RoundNumber MAX_NORMAL_FRAME_ROUND_NUMBER = new RoundNumber(9);

    private NormalFrame(RoundNumber roundNumber, NormalFrameScore frameScore) {
        super(roundNumber, frameScore);
    }

    public static Frame of(RoundNumber roundNumber, NormalFrameScore frameScore) {
        validateNormalRoundNumber(roundNumber);
        return new NormalFrame(roundNumber, frameScore);
    }

    private static void validateNormalRoundNumber(RoundNumber roundNumber) {
        if (RoundNumber.MAX_ROUND_NUMBER.equals(roundNumber)) {
            throw new IllegalNormalFrameException();
        }
    }

    public static Frame createFirstFrame() {
        return NormalFrame.of(RoundNumber.firstRoundNumber(), new NormalFrameScore());
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public Frame createNextFrame() {
        if (MAX_NORMAL_FRAME_ROUND_NUMBER.equals(roundNumber)) {
            return FinalFrame.from(new FinalFrameScore());
        }
        return NormalFrame.of(roundNumber.nextRoundNumber(), new NormalFrameScore());
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
