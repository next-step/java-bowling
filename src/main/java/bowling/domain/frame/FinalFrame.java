package bowling.domain.frame;

import bowling.domain.frame.state.FinalFrameStates;
import bowling.domain.frame.state.State;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {

    private static final String LAST_FRAME = "10번 이후의 프레임은 생성 불가";

    private FinalFrameStates states;

    public FinalFrame() {
        this.states = FinalFrameStates.of();
    }

    @Override
    public Frame bowl(int pinCount) {
        if (isFinish()) {
            throw new BowlingException(State.CANT_THROW_BALL);
        }

        states = states.bowl(pinCount);
        return this;
    }

    @Override
    public boolean isFinish() {
        return states.isFinish();
    }

    @Override
    public boolean isEnd() {
        return isFinish();
    }

    @Override
    public boolean isFrameFinish(final int frameNumber) {
        Frame frame = findFrame(frameNumber);
        if (Objects.isNull(frame)) {
            return false;
        }
        return frame.isFinish();
    }

    @Override
    public Frame createNext() {
        throw new BowlingException(LAST_FRAME);
    }

    @Override
    public Frame getNext() {
        return null;
    }

    @Override
    public State getState() {
        return states;
    }

    @Override
    public Frame findLast() {
        return this;
    }


    @Override
    public Score getCurrentScore() {
        return states.getCurrentScore();
    }

    @Override
    public Score getTotalScore(int frameNumber) {
        return getCurrentScore();
    }

    @Override
    public Score getCalculateScore(Score before) {
        return states.getCalculateScore(before);
    }

    @Override
    public Frame findFrame(int frameNumber) {
        return this;
    }

    @Override
    public List<Pins> getPins() {
        List<Pins> merge = new ArrayList<>();
        merge.add(states.getFirstPins());
        merge.add(states.getLastPins());

        return merge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states);
    }
}
