package bowling.domain.frame;

import bowling.domain.Pin;

public class NormalFrame extends Frame {

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public void bowl(Pin pin) {
        this.state = state.bowl(pin);
    }
}
