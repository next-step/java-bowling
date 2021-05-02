package bowling.domain.frame;

import bowling.domain.pin.FinalPins;
import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.exception.IllegalNormalFrameException;

public final class NormalFrame extends Frame {

    public static final RoundNumber MAX_NORMAL_FRAME_ROUND_NUMBER = new RoundNumber(RoundNumber.MAX - 1);

    private NormalFrame(RoundNumber roundNumber, NormalPins pins) {
        super(roundNumber, pins);
    }

    @Override
    public Frame nextFrame() {
        if (nextFrame == null) {
            createNextFrame();
        }
        return nextFrame;
    }

    public static Frame of(RoundNumber roundNumber, NormalPins pins) {
        validateNormalRoundNumber(roundNumber);
        return new NormalFrame(roundNumber, pins);
    }

    private static void validateNormalRoundNumber(RoundNumber roundNumber) {
        if (RoundNumber.MAX_ROUND_NUMBER.equals(roundNumber)) {
            throw new IllegalNormalFrameException();
        }
    }

    public static Frame createFirstFrame() {
        return NormalFrame.of(RoundNumber.firstRoundNumber(), NormalPins.create());
    }

    @Override
    public void createNextFrame() {
        this.nextFrame = generateNextFrame();
    }

    private Frame generateNextFrame() {
        if (MAX_NORMAL_FRAME_ROUND_NUMBER.equals(roundNumber)) {
            return FinalFrame.from(FinalPins.create());
        }
        return NormalFrame.of(roundNumber.nextRoundNumber(), NormalPins.create());
    }

    @Override
    public void knockDownPin(Pin pin) {
        pins.validatePinCount(pin);
        pins.knockDownPin(pin);
    }

    @Override
    public boolean isEnded() {
        return pins.isEnded();
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public Score score() {
        if (!isEnded()) {
            return Score.notCalculable();
        }
        if (pins.frameStatus() == FrameStatus.STRIKE) {
            return nextFrame().addScore(Score.strike());
        }
        if (pins.frameStatus() == FrameStatus.SPARE) {
            return nextFrame().addScore(Score.spare());
        }
        return Score.normal(pins.totalPinCount());
    }

    @Override
    protected Score addScore(Score previousScore) {
        if (pins.isEmpty()) {
            return Score.notCalculable();
        }

        final Score addedScore = previousScore.add(Score.normal(pins.firstPinCount()));
        if (!addedScore.canCalculate()) {
            return additionalScore(addedScore);
        }

        return addedScore;
    }

    private Score additionalScore(Score addedScore) {
        if (pins.isStrike()) {
            return nextFrame().addScore(addedScore);
        }
        return addedScore.add(Score.normal(pins.secondPinCount()));
    }
}
