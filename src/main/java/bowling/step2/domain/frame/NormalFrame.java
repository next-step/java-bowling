package bowling.step2.domain.frame;

import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {
    public State state;
    
    public NormalFrame() {
        this.state = new Ready();
    }
    
    @Override
    public NormalFrame bowl(final int fallenPins) {
        this.state = this.state.bowl(fallenPins);
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
