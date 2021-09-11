package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.CanNotCalculateException;
import bowling.exception.FrameNotCorrectException;

import static bowling.domain.score.Score.BONUS_REMAIN_COUNT_ONE;
import static bowling.domain.score.Score.BONUS_REMAIN_COUNT_TWO;

public final class FinalFrame extends Frame {

    private static final int FINAL_FRAME_ROUND_NUMBER = 10;
    private static final int MAX_SIZE = 3;
    private static final int MIN_SIZE = 2;

    private FinalFrame(final Pins pins) {
        super(FINAL_FRAME_ROUND_NUMBER, pins);
    }

    public static Frame of() {
        return new FinalFrame(Pins.of());
    }

    public static Frame of(final int... pins) {
        return new FinalFrame(Pins.of(pins));
    }

    public static Frame of(final Pins pins) {
        return new FinalFrame(pins);
    }

    @Override
    protected void validateFrame(final Pins pins) {
        if (pins.isSecondPinWrong()) {
            throw new FrameNotCorrectException();
        }
        if (pins.isThirdPinWrong()) {
            throw new FrameNotCorrectException();
        }
    }

    @Override
    public boolean isFinished() {
        if (pins.size() == MAX_SIZE) {
            return true;
        }
        if (pins.size() < MIN_SIZE) {
            return false;
        }
        return pins.sum() < 10;
    }

    @Override
    public Frame bowl(final int knockDownNumber) {
        pins.add(knockDownNumber);
        validateFrame(pins);
        return this;
    }

    @Override
    public Frame next() {
        return this;
    }

    @Override
    public boolean canCalculateScore() {
        return isFinished();
    }

    @Override
    public int getScore() {
        if (!canCalculateScore()) {
            throw new CanNotCalculateException();
        }
        return pins.sum();
    }

    @Override
    public int addScore(final Score score) {
        if (score.isRemainCount(BONUS_REMAIN_COUNT_ONE)) {
            return score.sum(firstPin().getKnockDownNumber());
        }

        if (score.isRemainCount(BONUS_REMAIN_COUNT_TWO)) {
            return score.sum(firstPin().sum(secondPin()));
        }
        return score.sum(pins.sum());
    }
}
