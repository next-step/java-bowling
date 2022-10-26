package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;

import java.util.Optional;

public class FinalFrame extends Frame {

    private Pin bonus;

    public FinalFrame() {
        super();
    }

    public FinalFrame(Frame before) {
        super(before);
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
            addPoint();
            return;
        }
        if (state.canGetBonus()) {
            this.bonus = pin;
            addPoint();
            return;
        }

        throw new UnsupportedOperationException();
    }

    private void addPoint() {
        if (isFinish()) {
            this.point = new Point(before.point, state.getSum() + getBonusValue(), 0);
        }
    }

    @Override
    public Integer getPoint() {

        return Optional.ofNullable(point)
                .map(Point::point)
                .map(point -> point + getBonusValue())
                .orElse(null);

    }

    private Integer getBonusValue() {
        return Optional.ofNullable(bonus)
                .map(Pin::getValue)
                .orElse(0);
    }

    @Override
    public boolean canAddPoint() {
        return false;
    }

    @Override
    public void addPoint(Pin pin) {
        throw new UnsupportedOperationException();
    }

    public Pin getBonus() {
        return bonus;
    }
}
