package bowling.domain.frame;

import bowling.domain.pin.BallThrows;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.exception.NoNextFrameException;

public final class FinalFrame extends Frame {

    public static final int THROW_TWICE = 2;
    public static final int THROW_THREE_TIMES = 3;
    public static final int BONUS_GAME_THRESHOLD = 10;

    private FinalFrame(RoundNumber roundNumber, BallThrows ballThrows) {
        super(roundNumber, ballThrows);
    }

    public static FinalFrame create() {
        return new FinalFrame(RoundNumber.MAX_ROUND_NUMBER, BallThrows.ofFinal());
    }

    public static FinalFrame from(BallThrows ballThrows) {
        return new FinalFrame(RoundNumber.MAX_ROUND_NUMBER, ballThrows);
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
        ballThrows.knockDownPin(pin);
    }

    @Override
    public boolean isEnded() {
        if (ballThrows.throwCount() == THROW_TWICE) {
            return ballThrows.totalPinCount() < BONUS_GAME_THRESHOLD;
        }
        return ballThrows.throwCount() == THROW_THREE_TIMES;
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
        if (ballThrows.throwCount() < THROW_TWICE) {
            return Score.notCalculable();
        }
        return addedScore.add(Score.normal(ballThrows.secondPinCount()));
    }
}
