package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.exception.IllegalNormalFrameException;

public final class NormalFrame extends Frame {

    public static final int MAX_NORMAL_FRAME_ROUND_NUMBER = 9;

    private NormalFrame(RoundNumber roundNumber, FrameScore frameScore) {
        super(roundNumber, frameScore);
    }

    public static Frame of(RoundNumber roundNumber, FrameScore frameScore) {
        validateNormalRoundNumber(roundNumber);
        return new NormalFrame(roundNumber, frameScore);
    }

    private static void validateNormalRoundNumber(RoundNumber roundNumber) {
        if (RoundNumber.MAX_ROUND_NUMBER.equals(roundNumber)) {
            throw new IllegalNormalFrameException();
        }
    }

    public static Frame createFirstFrame() {
        return NormalFrame.of(RoundNumber.firstRoundNumber(), new FrameScore());
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public Frame createNextFrame() {
        final RoundNumber roundNumber = roundNumber();
        if (roundNumber.value() == MAX_NORMAL_FRAME_ROUND_NUMBER) {
            return FinalFrame.from(new FrameScore());
        }
        return NormalFrame.of(roundNumber.nextRoundNumber(), new FrameScore());
    }

    @Override
    public void knockDownPin(Pin pin) {

    }

    @Override
    public String status() {
        return frameScore().status();
    }
}
