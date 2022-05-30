package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pin;
import bowling.domain.state.States;

import static bowling.view.OutputView.FINAL_STATE_FORMAT;

public class FinalFrame extends Frame {
    private final States states;

    public FinalFrame(FrameNumber frameNumber) {
        this(frameNumber, States.initialize());
    }

    public FinalFrame(FrameNumber frameNumber, States states) {
        super(frameNumber);
        validate(frameNumber, states);
        this.states = states;
    }

    private void validate(FrameNumber frameNumber, States states) {
        if (frameNumber.isNormal()) {
            throw new IllegalArgumentException(
                    String.format("frameNumber(%s)는 마지막 FrameNumber(%s)가 아닙니다.", frameNumber, FrameNumber.MAX));
        }

        if (states == null) {
            throw new IllegalArgumentException("states는 null 일 수 없습니다.");
        }
    }

    @Override
    public Frame bowl(Pin pin) {
        states.bowl(pin);
        return null;
    }

    @Override
    public Frame next() {
        throw new IllegalArgumentException("마지막 프레임은 다음 프레임을 생성 할 수 없습니다.");
    }

    @Override
    public boolean isNormal() {
        return false;
    }

    @Override
    public boolean isDone() {
        return states.isDone();
    }

    @Override
    public Score score() {
        return states.score();
    }

    @Override
    public Score score(Score score) {
        return states.score(score);
    }

    @Override
    public String toString() {
        return String.format(FINAL_STATE_FORMAT, states.toString());
    }
}
