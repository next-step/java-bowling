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
            sumPoint();
            return;
        }
        if (state.canGetBonus()) {
            this.bonus = pin;
            sumPoint();
            return;
        }

        throw new UnsupportedOperationException();
    }

    private void sumPoint() {
        if (isFinish()) {
            int nowSum = state.getSum() + calculateBonus();
            Point beforePoint = Optional.ofNullable(before)
                    .map(frame -> frame.point)
                    .orElse(null);
            this.point = new Point(beforePoint, nowSum, 0);
        }
    }

    @Override
    public Integer calculatePoint() {

        if (point != null && bonus != null) {
            return point.point() + bonus.getValue();
        }
        return null;
    }

    private int calculateBonus() {
        if (bonus != null) {
            return bonus.getValue();
        }
        return 0;
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
