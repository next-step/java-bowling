package bowling.strategy;

import bowling.domain.Pin;

public class TestInputBowlingStrategy implements BowlingStrategy {

    @Override
    public Pin drawBowl(Pin pin, int testNumber) {
        if(pin.isAllClear()) {
            return new Pin(0,0);
        }
        return new Pin(pin.leftPins(), testNumber);
    }

}
