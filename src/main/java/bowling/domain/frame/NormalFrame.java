package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.CanNotCalculateException;
import bowling.exception.FrameNotCorrectException;

import static bowling.domain.frame.FrameStatus.*;
import static bowling.domain.score.Score.BONUS_REMAIN_COUNT_ONE;
import static bowling.domain.score.Score.BONUS_REMAIN_COUNT_TWO;

public final class NormalFrame extends Frame {

    public static final int FIRST_ROUND_NUMBER = 1;
    public static final int NORMAL_LAST_ROUND = 9;
    private static final int MAX_SIZE = 2;
    private static final int MAX_KNOCK_DOWN_NUMBER = 10;

    private NormalFrame(final int roundNumber) {
        super(roundNumber, Pins.of());
    }

    private NormalFrame(final int roundNumber, final Pins pins) {
        super(roundNumber, pins);
    }

    private NormalFrame(final int roundNumber, final Pins pins, final Frame frame) {
        super(roundNumber, pins, frame);
    }

    public static Frame of(final int roundNumber, final int... number) {
        return new NormalFrame(roundNumber, Pins.of(number));
    }

    public static Frame of(final int roundNumber, Pins pins) {
        return new NormalFrame(roundNumber, pins);
    }

    public static Frame of(final int roundNumber, Pins pins, Frame frame) {
        return new NormalFrame(roundNumber, pins, frame);
    }

    public static Frame of(final int roundNumber) {
        return new NormalFrame(roundNumber);
    }

    public static Frame first() {
        return new NormalFrame(FIRST_ROUND_NUMBER);
    }

    public static Frame next(final Frame frame) {
        if (frame.roundNumber == NORMAL_LAST_ROUND) {
            frame.nextFrame = FinalFrame.of();
            return frame.nextFrame;
        }
        frame.nextFrame = new NormalFrame(frame.roundNumber + NEXT_ROUND_NUMBER_DISTANCE, Pins.of());
        return frame.nextFrame;
    }

    @Override
    protected void validateFrame(final Pins pins) {
        if (pins.sum() > MAX_KNOCK_DOWN_NUMBER) {
            throw new FrameNotCorrectException();
        }
    }

    @Override
    public boolean isFinished() {
        if (pins.isStrike()) {
            return true;
        }
        return pins.size() == MAX_SIZE;
    }

    @Override
    public Frame bowl(final int knockDownNumber) {
        pins.add(knockDownNumber);
        validateFrame(pins);
        return this;
    }

    @Override
    public Frame next() {
        if (isFinished()) {
            return NormalFrame.next(this);
        }
        return this;
    }

    @Override
    public boolean canCalculateScore() {
        if (!isFinished()) {
            return false;
        }

        if (FrameStatus.of(pins) == NORMAL) {
            return true;
        }

        if (FrameStatus.of(pins) == SPARE && !nextPinEmpty()) {
            return true;
        }

        if (nextPinSize() == MAX_SIZE) {
            return true;
        }

        return !nextPinEmpty() && !nextFrame.nextPinEmpty();
    }

    @Override
    public int getScore() {
        if (!canCalculateScore()) {
            throw new CanNotCalculateException();
        }

        if (FrameStatus.of(pins) == STRIKE) {
            return nextFrame.addScore(Score.ofRemainTwo(pins.sum()));
        }

        if (FrameStatus.of(pins) == SPARE) {
            return nextFrame.addScore(Score.ofRemainOne(pins.sum()));
        }
        return pins.sum();
    }

    @Override
    public int addScore(final Score score) {
        if (FrameStatus.of(pins) == STRIKE && score.isRemainCount(BONUS_REMAIN_COUNT_TWO)) {
            int addScore = score.sum(firstPinNumber());
            return nextFrame.addScore(Score.ofRemainOne(addScore));
        }

        if (score.isRemainCount(BONUS_REMAIN_COUNT_ONE)) {
            return score.sum(firstPinNumber());
        }
        return score.sum(pins.sum());
    }
}