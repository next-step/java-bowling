package bowling.domain.frame;

import bowling.domain.FrameNo;
import bowling.domain.Score;
import bowling.domain.state.States;
import bowling.exception.CannotCalculateScore;
import bowling.exception.NotCreateFrameException;

import java.util.Objects;

public class FinalFrame implements Frame {

    private final FrameNo frameNo;
    private final States states;

    public FinalFrame(FrameNo frameNo) {
        this.frameNo = frameNo;
        this.states = States.initialize();
    }

    @Override
    public Frame next() throws NotCreateFrameException {
        throw new NotCreateFrameException(frameNo.toInt());
    }

    @Override
    public void bowling(int hit) {
        states.bowl(hit);
    }

    @Override
    public Score calculate(Score score) {
        return states.calculate(score);
    }

    @Override
    public boolean isFinish() {
        return states.isFinish();
    }

    @Override
    public boolean isProgressing() {
        return states.isProgressing();
    }

    @Override
    public FrameNo frameNo() {
        return frameNo;
    }

    @Override
    public Score score() {
        if (!isFinish()) {
            throw new CannotCalculateScore();
        }
        return states.score();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNo, that.frameNo) && Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, states);
    }

    @Override
    public String toString() {
        return states.description();
    }
}
