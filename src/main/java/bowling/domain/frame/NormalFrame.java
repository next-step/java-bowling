package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.CanNotCalculateException;
import bowling.exception.FrameNotCorrectException;

public final class NormalFrame extends Frame {

    public static final int FIRST_ROUND_NUMBER = 1;
    public static final int ROUND_BEFORE_LAST_ROUND = 9;
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

    public static Frame next(Frame frame) {
        Frame nextFrame = new NormalFrame(frame.roundNumber + 1, Pins.of(), frame);
        frame.setNextFrame(nextFrame);
        return nextFrame;
    }

    @Override
    protected void validateFrame(final Pins pins) {
        if (pins.sum() > MAX_KNOCK_DOWN_NUMBER) {
            throw new FrameNotCorrectException();
        }
    }

    @Override
    public Frame bowl(final int knockDownNumber) {
        pins.add(knockDownNumber);
        Frame newFrame = NormalFrame.of(roundNumber, pins);
        newFrame.setNextFrame(nextFrame);
        return newFrame;
    }

    @Override
    public boolean isFinished() {
        if (pins.isStrike()) {
            return true;
        }
        return pins.size() == MAX_SIZE;
    }

    @Override
    public Frame nextFrame() {
        if (pins.size() == 0) {
            return this;
        }

        if (pins.size() == 1 && FrameStatus.of(pins) != FrameStatus.STRIKE) {
            return this;
        }

        if (roundNumber == ROUND_BEFORE_LAST_ROUND) {
            return FinalFrame.of();
        }
        return NormalFrame.next(this);
    }

    @Override
    public boolean canCalculateScore() {
        if (!isFinished()) {
            return false;
        }

        if (FrameStatus.of(pins) == FrameStatus.NORMAL) {
            return true;
        }

        if (FrameStatus.of(pins) == FrameStatus.SPARE) {
            if (nextFrame == null) {
                return false;
            }
            if (nextFrame.pins.size() > 0) {
                return true;
            }
        }

        if (FrameStatus.of(pins) == FrameStatus.STRIKE) {
            if (nextFrame != null && nextFrame.pins.size() == 2 && FrameStatus.of(nextFrame.pins) != FrameStatus.STRIKE) {
                return true;
            }

            if (nextFrame != null && FrameStatus.of(nextFrame.pins) == FrameStatus.STRIKE) {
                if (nextFrame.nextFrame != null && nextFrame.nextFrame.pins.size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getScore() {
        if (!canCalculateScore()) {
            throw new CanNotCalculateException();
        }

        if (FrameStatus.of(pins) == FrameStatus.STRIKE) {
            return nextFrame.addScore(Score.ofRemainTwo(pins.sum()));
        }

        if (FrameStatus.of(pins) == FrameStatus.SPARE) {
            return nextFrame.addScore(Score.ofRemainOne(pins.sum()));
        }
        return pins.sum();
    }

    @Override
    public int addScore(Score score) {
        if (FrameStatus.of(pins) == FrameStatus.STRIKE && score.isRemainCount(Score.BONUS_REMAIN_COUNT_TWO)) {
            int sumScore = score.sum(firstPin().getKnockDownNumber());
            return nextFrame.addScore(Score.ofRemainOne(sumScore));
        }

        if (score.isRemainCount(Score.BONUS_REMAIN_COUNT_ONE)) {
            return score.sum(firstPin().getKnockDownNumber());
        }
        return score.sum(pins.sum());
    }
}