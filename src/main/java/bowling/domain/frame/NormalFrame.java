package bowling.domain.frame;

import bowling.domain.State.Pin;
import bowling.domain.State.State;

import static bowling.view.OutputView.NORMAL_STATE_FORMAT;

public class NormalFrame extends Frame {
    private State state;
    private Frame next;

    public NormalFrame(FrameNumber frameNumber) {
        this(frameNumber, State.ready());
    }

    public NormalFrame(FrameNumber frameNumber, State state) {
        super(frameNumber);
        validate(frameNumber, state);
        this.state = state;
    }

    private void validate(FrameNumber frameNumber, State state) {
        if (!frameNumber.isNormal()) {
            throw new IllegalArgumentException(
                    String.format("frameNumber(%s)는 NormalFrame 범위(%s <= n <= %s)에 있지 않습니다.",
                            frameNumber, FrameNumber.MIN, FrameNumber.MAX_IN_NORMAL_FRAME));
        }

        if (state == null) {
            throw new IllegalArgumentException("state는 null 일 수 없습니다.");
        }
    }

    @Override
    public Frame bowl(Pin pin) {
        state = state.bowl(pin);
        return next();
    }

    @Override
    public Frame next() {
        if (frameNumber.isMaxInNormal()) {
            next = new FinalFrame(frameNumber.next());
            return next;
        }
        next = new NormalFrame(frameNumber.next());
        return next;
    }

    @Override
    public boolean isNormal() {
        return true;
    }

    @Override
    public boolean isDone() {
        return state.isDone();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String toString() {
        return String.format(NORMAL_STATE_FORMAT, state.toString());
    }
}
