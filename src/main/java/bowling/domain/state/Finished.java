package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Pins;

import java.util.List;

public abstract class Finished implements State {

    private Pins pins;

    protected Finished(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(Pin pin) {
        throw new IllegalStateException("종료 되었습니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public List<Integer> getRecord() {
        return pins.getRecord();
    }

    @Override
    public int getRemainPins() {
        return pins.getRemainPins();
    }
}
