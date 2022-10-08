package bowling.step2.domain;

import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

import java.util.Objects;

public class NormalFrame {
    public State state;
    
    public NormalFrame() {
        this.state = new Ready();
    }
    
    public NormalFrame bowl(final int remainingPins) {
        this.state = this.state.bowl(remainingPins);
        if (state.isFinished()) {
            return new NormalFrame();
        }
        
        return this;
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
