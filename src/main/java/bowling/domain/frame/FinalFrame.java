package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;

import java.util.Optional;

public class FinalFrame extends Frame {

    private Pin bonus;

    public FinalFrame(Point before) {
        super(before);
    }

    public static FinalFrame of(Frame frame){
        return new FinalFrame(frame.point);
    }

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
            this.point.add(pin);
            return;
        }
        if (state.canGetBonus()) {
            this.bonus = pin;
            this.point.add(pin);
            return;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canAddPoint() {
        return false;
    }

    @Override
    public void addPoint(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Pin> getBonus() {
        return Optional.ofNullable(bonus);
    }
}
