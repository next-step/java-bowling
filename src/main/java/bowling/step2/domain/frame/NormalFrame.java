package bowling.step2.domain.frame;

import bowling.step2.domain.FrameOrderNumber;
import bowling.step2.domain.Score;
import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    private State state;
    private final FrameOrderNumber frameOrderNumber;
    
    
    public NormalFrame(int frameOrderNumber) {
        this.state = new Ready();
        this.frameOrderNumber = new FrameOrderNumber(frameOrderNumber);
    }
    
    @Override
    public Frame bowl(final int fallenPins) {
        this.state = this.state.bowl(fallenPins);
        if (state.isFinished()) {
            return nextFrame();
        }
        
        return this;
    }
    
    private Frame nextFrame() {
        if (frameOrderNumber.isNextFinalFrame()) {
            return new FinalFrame();
        }
        return new NormalFrame(frameOrderNumber.increase());
    }
    
    @Override
    public boolean isNormalFrame() {
        return true;
    }
    
    @Override
    public List<Score> getScores() {
        return state.getScores();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NormalFrame that = (NormalFrame) o;
        return Objects.equals(state, that.state);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
