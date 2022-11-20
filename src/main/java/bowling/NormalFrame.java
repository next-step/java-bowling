package bowling;

import bowling.state.Ready;
import bowling.state.State;

public class NormalFrame implements Frame {

    public static final int MIN_NORMAL_FRAME_NUMBER = 1;
    public static final int MAX_NORMAL_FRAME_NUMBER = 9;

    private final int frameNumber;
    private State state;
    private Frame next;

    private NormalFrame(int frameNumber, State state) {
        this.frameNumber = frameNumber;
        this.state = state;
    }

    public static Frame first() {
        return new NormalFrame(MIN_NORMAL_FRAME_NUMBER, new Ready());
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public Frame nextFrame() {
        if (frameNumber == MAX_NORMAL_FRAME_NUMBER) {
            next = FinalFrame.start();
            return next;
        }
        next = new NormalFrame(frameNumber + 1, new Ready());
        return next;
    }

    @Override
    public Frame bowl(Pin falledPins) {
        state = state.bowl(falledPins);
        if (state.isFinished()) {
            return nextFrame();
        }
        return this;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public Score getScores() {
        Score score = state.score();
        if (score.canCalculate()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score afterScore = state.calculateAdditionalScore(beforeScore);
        if (afterScore.canCalculate() || next == null) {
            return afterScore;
        }
        return next.calculateAdditionalScore(afterScore);
    }

    @Override
    public String getDesc() {
        return state.getDesc();
    }
}
