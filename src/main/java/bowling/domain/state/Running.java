package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Pins;

import java.util.List;

public class Running extends Started {

    private Pins pins;

    public Running(Pin pin) {
        if (pin.isStrike()) {
            throw new IllegalArgumentException();
        }
        this.pins = Pins.of(pin);
    }

    @Override
    public State bowl(Pin pin) {
        pins.bowl(pin);
        if (pins.isSpare()) {
            return new Spare(pins);
        }

        if (pins.isMiss()) {
            return new Miss(pins);
        }
        throw new IllegalStateException();
    }

    @Override
    public int getRemainPins() {
        return pins.getRemainPins();
    }


    @Override
    public List<Integer> getRecord() {
        return pins.getRecord();
    }

}
