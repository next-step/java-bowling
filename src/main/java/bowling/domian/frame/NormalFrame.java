package bowling.domian.frame;

import bowling.domian.state.running.Ready;
import bowling.domian.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final int frameNumber;

    private State state;
    private Frame next;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(1);
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Frame bowl(int falledPinsCount) {
        this.state = this.state.bowl(falledPinsCount);

        if (state.isFinished()) {
            this.next = getNextFrame();
            return this.next;
        }

        return this;
    }

    private Frame getNextFrame() {
        if (frameNumber < 9) {
            return new NormalFrame(frameNumber + 1);
        }

        return new FinalFrame();
    }

    public FrameResult getFrameResult() {
        if (!this.state.canGetScore()) {
            return FrameResult.of(this.state.getDesc());
        }

        return FrameResult.of(getScore(), state.getDesc());
    }

    private Score getScore() {
        Score score = this.state.getScore();

        if (score.isCalculateDone()) {
            return score;
        }

        return next.calculateAdditional(score);
    }

    @Override
    public Score calculateAdditional(Score score) {
        if (this.state instanceof Ready) {
            return score;
        }

        score = this.state.calculateAdditional(score);
        if (needCalculateAdditionalScore(score)) {
            return next.calculateAdditional(score);
        }

        return score;
    }

    private boolean needCalculateAdditionalScore(Score score) {
        return !score.isCalculateDone() && Objects.nonNull(next);
    }

    @Override
    public Board addBoard(Board board) {
        if (state instanceof Ready) {
            return board;
        }

        board.addFrameResult(getFrameResult());

        return next.addBoard(board);
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNumber == that.frameNumber &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, state);
    }
}
