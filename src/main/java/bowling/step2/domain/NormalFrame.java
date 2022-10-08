package bowling.step2.domain;

import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

public class NormalFrame {
    public State state;
    
    public NormalFrame() {
        this.state = new Ready();
    }
    
    public NormalFrame bowl(final int remainingPins) {
        this.state = this.state.bowl(remainingPins);
        
        return this;
    }
}
