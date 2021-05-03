package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.PinCountValidator;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.NoNextFrameException;

public final class FinalFrame extends Frame {

    public static final int THROW_TWICE = 2;
    public static final int THROW_THREE_TIMES = 3;
    public static final int BONUS_GAME_THRESHOLD = 10;
    private static final int SECOND_PIN_EXIST_SIZE = 2;

    private FinalFrame(RoundNumber roundNumber, Pins pins) {
        super(roundNumber, pins);
    }

    public static FinalFrame create() {
        return new FinalFrame(RoundNumber.MAX_ROUND_NUMBER, Pins.create());
    }

    public static FinalFrame from(Pins pins) {
        return new FinalFrame(RoundNumber.MAX_ROUND_NUMBER, pins);
    }

    @Override
    public Frame nextFrame() {
        throw new NoNextFrameException();
    }

    @Override
    public void createNextFrame() {
        throw new NoNextFrameException();
    }

    @Override
    public void knockDownPin(Pin pin) {
        pins.validatePinCount(pin, PinCountValidator.FINAL);
        pins.knockDownPin(pin);
    }

    @Override
    public boolean isEnded() {
        if (pins.size() == THROW_TWICE) {
            return pins.totalPinCount() < BONUS_GAME_THRESHOLD;
        }
        return pins.size() == THROW_THREE_TIMES;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public Score score() {
        if (!isEnded()) {
            return Score.notCalculable();
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
            return previousStrikeScore(addedScore);
        }

        return addedScore;
    }

    private Score previousStrikeScore(Score addedScore) {
        if (pins.size() < SECOND_PIN_EXIST_SIZE) {
            return Score.notCalculable();
        }
        return addedScore.add(Score.normal(pins.secondPinCount()));
    }
}
