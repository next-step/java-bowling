package bowling.domain.frame;

import bowling.domain.pin.BallThrows;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.exception.IllegalNormalFrameException;

public final class NormalFrame extends Frame {

    public static final RoundNumber MAX_NORMAL_FRAME_ROUND_NUMBER = new RoundNumber(RoundNumber.MAX - 1);
    public static final int MAX_NORMAL_PIN_COUNT = 10;
    private static final int THROW_TWICE = 2;

    private NormalFrame(RoundNumber roundNumber, BallThrows ballThrows) {
        super(roundNumber, ballThrows);
    }

    public static Frame from(RoundNumber roundNumber) {
        return NormalFrame.of(roundNumber, BallThrows.create());
    }

    public static Frame of(RoundNumber roundNumber, BallThrows ballThrows) {
        validateNormalRoundNumber(roundNumber);
        return new NormalFrame(roundNumber, ballThrows);
    }

    private static void validateNormalRoundNumber(RoundNumber roundNumber) {
        if (RoundNumber.MAX_ROUND_NUMBER.equals(roundNumber)) {
            throw new IllegalNormalFrameException();
        }
    }

    public static Frame createFirstFrame() {
        return NormalFrame.of(RoundNumber.firstRoundNumber(), BallThrows.create());
    }

    @Override
    public Frame nextFrame() {
        if (nextFrame == null) {
            createNextFrame();
        }
        return nextFrame;
    }

    @Override
    public void createNextFrame() {
        this.nextFrame = generateNextFrame();
    }

    private Frame generateNextFrame() {
        if (MAX_NORMAL_FRAME_ROUND_NUMBER.equals(roundNumber)) {
            return FinalFrame.create();
        }
        return NormalFrame.from(roundNumber.nextRoundNumber());
    }

    @Override
    public void knockDownPin(Pin pin) {
        ballThrows.knockDownPin(pin);
    }

    @Override
    public boolean isEnded() {
        return ballThrows.isEnded();
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
        if (ballThrows.frameStatus() == FrameStatus.STRIKE) {
            return nextFrame().addScore(Score.strike());
        }
        if (ballThrows.frameStatus() == FrameStatus.SPARE) {
            return nextFrame().addScore(Score.spare());
        }
        return Score.normal(ballThrows.totalPinCount());
    }

    @Override
    protected Score addScore(Score previousScore) {
        if (ballThrows.isFirstThrow()) {
            return Score.notCalculable();
        }

        final Score addedScore = previousScore.add(Score.normal(ballThrows.firstPinCount()));
        if (!addedScore.canCalculate()) {
            return previousStrikeScore(addedScore);
        }

        return addedScore;
    }

    private Score previousStrikeScore(Score addedScore) {
        if (ballThrows.isStrike()) {
            return nextFrame().addScore(addedScore);
        }
        if (ballThrows.throwCount() < THROW_TWICE) {
            return Score.notCalculable();
        }
        return addedScore.add(Score.normal(ballThrows.secondPinCount()));
    }
}
