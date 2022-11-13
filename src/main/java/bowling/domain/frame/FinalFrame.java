package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinalFrame extends Frame {

    private List<Pin> bonus = new ArrayList<>();

    public FinalFrame(Point before) {
        super(before);
    }

    public static FinalFrame of(Frame frame){
        return new FinalFrame(frame.point);
    }

    @Override
    public boolean isFinish() {
        if (state.isFinish() && state.canGetBonus()) {
            return bonus.size() == state.bonusCount();
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
        if (state.canGetBonus() &&  bonus.size() < state.bonusCount()) {
            this.bonus.add(pin);
            this.point.add(pin);
            return;
        }

        throw new IllegalStateException();
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
    public List<Pin> getBonus() {
        return Collections.unmodifiableList(bonus);
    }
}
