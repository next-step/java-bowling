package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.Optional;

public class NormalFrame extends Frame {
    private final Frame nextFrame;

    public NormalFrame(final FrameNumber frameNumber) {
        super(frameNumber);
        this.nextFrame = createNextFrame();
    }

    public static Frame ofFirst() {
        return new NormalFrame(FrameNumber.ofFirst());
    }

    @Override
    public Frame bowl(final Pins pins) {
        State currentState = getCurrentState();
        states.add(currentState.roll(pins));
        if (!isFinish()) {
            return this;
        }
        return nextFrame;
    }

    @Override
    public boolean isFinish() {
        if (states.isEmpty()) {
            return false;
        }
        return states.getLast().isTurnOver();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.of(nextFrame);
    }

    @Override
    public Score getScore() {
        if (states.isEmpty() || !isFinish()) {
            return Score.NOT_ADDABLE_SCORE;
        }

        Score totalScore = sum();
        if (totalScore.isCompleteAccumulation()) {
            return totalScore;
        }

        return nextFrame.calculateAdditionalScore(totalScore);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        if (states.isEmpty()) {
            return Score.NOT_ADDABLE_SCORE;
        }

        Score totalScore = calculate(beforeScore);
        if (totalScore.isCompleteAccumulation()) {
            return totalScore;
        }

        return nextFrame.calculateAdditionalScore(totalScore);
    }

    private Frame createNextFrame() {
        final FrameNumber nextFrameNumber = frameNumber.increase();
        if (nextFrameNumber.isFinal()) {
            return new FinalFrame(nextFrameNumber);
        }
        return new NormalFrame(nextFrameNumber);
    }
}
