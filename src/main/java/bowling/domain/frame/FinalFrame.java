package bowling.domain.frame;

import bowling.domain.Pin;

public class FinalFrame extends Frame {

    private Pin bonus;

    @Override
    public boolean isFinish() {
        if (state.isFinish() && state.canGetBonus()) {
            return bonus != null;
        }
        return state.isFinish() && !state.canGetBonus();
    }

    @Override
    public void bowl(Pin pin) {
        if (!state.isFinish()) {
            this.state = this.state.bowl(pin);
            return;
        }
        if (state.canGetBonus()) {
            this.bonus = pin;
            return;
        }
        throw new IllegalStateException();
    }

    public Pin getBonus() {
        return bonus;
    }
}
