package bowling.step2.domain.frame;

import bowling.step2.domain.No;
import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {
    private State state;
    private No no;
    
    
    public NormalFrame(int no) {
        this.state = new Ready();
        this.no = new No(no);
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
        if (no.isNextFinalFrame()) {
            return new FinalFrame();
        }
        return new NormalFrame(no.increase());
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
