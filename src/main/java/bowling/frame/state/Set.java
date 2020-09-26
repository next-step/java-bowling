package bowling.frame.state;

import bowling.score.Pin;

public class Set extends Progress {

    @Override
    public State bowl(String falledPins) {
        Pin pin = Pin.bowl(falledPins);
        if (pin.isStrike()) {
            return Strike.of(pin);
        }
        return Next.bowl(pin);
    }
}
