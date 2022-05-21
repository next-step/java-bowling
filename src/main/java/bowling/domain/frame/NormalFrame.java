package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.Ready;
import bowling.domain.Score;
import bowling.domain.state.State;

public class NormalFrame implements Frame {

    private static final int INIT_FRAME_NO = 1;

    private final FrameNo frameNumber;

    private State state;

    private Frame next;

    NormalFrame(int frameNo, State state) {
        this(frameNo, state, null);
    }

    NormalFrame(int frameNo, State state, Frame next) {
        this.frameNumber = FrameNo.of(frameNo);
        this.state = state;
        this.next = next;
    }

    static Frame init() {
        return new NormalFrame(INIT_FRAME_NO, new Ready());
    }

    @Override
    public int number() {
        return frameNumber.no();
    }

    @Override
    public Frame bowl(Pin pin) {
        state = state.bowl(pin);
        if (state.finished()) {
            return generateNextFrame();
        }
        return this;
    }

    private Frame generateNextFrame() {
        FrameNo nextFrameNo = FrameNo.of(frameNumber.next());
        if (nextFrameNo.isMax()) {
            this.next = new FinalFrame();
            return this.next;
        }
        this.next = new NormalFrame(frameNumber.next(), new Ready());
        return this.next;
    }

    @Override
    public Score score() {
        Score score = state.score();
        if (score.canGetScore() || next == null) {
            return score;
        }
        return next.additionalScore(score);
    }

    @Override
    public boolean finished() {
        return state.finished();
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        Score afterScore = state.additionalScore(beforeScore);
        if (afterScore.canGetScore() || next == null) {
            return afterScore;
        }
        return next.additionalScore(afterScore);
    }

    @Override
    public String expression() {
        return state.expression();
    }
}
