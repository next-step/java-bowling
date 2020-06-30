package bowling.domain.frame;


import bowling.domain.dto.StateDtos;
import bowling.domain.pin.PinCount;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;

import java.util.Objects;

public class NormalFrame extends Frame {

    private State state;
    private final FrameNumber frameNumber;
    private Frame nextFrame;

    private NormalFrame(final FrameNumber frameNumber) {
        this.state = Ready.getInstance();
        this.frameNumber = frameNumber;
    }

    public static NormalFrame ofFirst() {
        return new NormalFrame(FrameNumber.of(FrameNumber.MIN_NUMBER));
    }

    public static NormalFrame newInstance(final FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public void bowl(final PinCount hitCount) {
        this.state = this.state.bowl(hitCount);
    }

    @Override
    public Frame initNextFrame() {
        if (this.isFinish()) {
            return createNextFrame();
        }
        return this;
    }

    private boolean isFinish() {
        return this.state.isFinish();
    }

    private Frame createNextFrame() {
        FrameNumber nextNumber = this.frameNumber.increase();

        if (nextNumber.isFinal()) {
            this.nextFrame = FinalFrame.newInstance();
            return this.nextFrame;
        }
        this.nextFrame = NormalFrame.newInstance(nextNumber);
        return this.nextFrame;
    }

    @Override
    void addFrame(final Frames frames) {
        if (isFinish()) {
            frames.add(initNextFrame());
        }
    }

    @Override
    public int getFrameNo() {
        return this.frameNumber.getNo();
    }

    @Override
    public StateDtos getFrameResult() {
        return StateDtos.of(state.getState());
    }

    @Override
    public Score getScore() {
        if (this.validateIsNotAddableScore()) {
            return Score.UN_SCORE;
        }

        final Score score = state.getScore();
        if (score.isZeroOfExtraBonusCount()) {
            return score;
        }

        return nextFrame.addBonusScore(score);
    }

    @Override
    public Score addBonusScore(final Score beforeScore) {
        final Score score = state.calculateScoreForExtraBonusCount(beforeScore);
        if (this.validateIsNotAddableScore()) {
            return Score.UN_SCORE;
        }

        return nextFrame.addBonusScore(score);
    }

    private boolean validateIsNotAddableScore() {
        return !isFinish() || Objects.isNull(nextFrame);
    }
}
