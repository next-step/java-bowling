package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.States;
import bowling.domain.value.Pins;

public class FinalFrame extends Frame {
    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.states = new States(new Ready());
    }

    @Override
    public Frame bowl(int knockedDownPin) {
        whenFinishResetStateForBonusBowl();
        states.add(states.bowl(new Pins(knockedDownPin)));
        return this;
    }

    private void whenFinishResetStateForBonusBowl() {
        if(states.isFinished()) {
            states.add(new Ready());
        }
    }
}
