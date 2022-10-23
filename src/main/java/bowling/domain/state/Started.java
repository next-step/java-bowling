package bowling.domain.state;

import bowling.domain.Pin;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Started implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isStrike()) {
            return new Strike(pin);
        }

        return new Running(pin);
    }

    @Override
    public int bonusCount() {
        return 0;
    }

    @Override
    public boolean canGetBonus() {
        return false;
    }

    @Override
    public int getRemainPins() {
        return 10;
    }

    @Override
    public List<Integer> getRecord() {
        return Collections.unmodifiableList(new LinkedList<>());
    }

    @Override
    public int getSum() {
        return 0;
    }

}
