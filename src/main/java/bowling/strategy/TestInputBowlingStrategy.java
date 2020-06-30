package bowling.strategy;

import bowling.domain.Pin;

public class TestInputBowlingStrategy implements BowlingStrategy {

    @Override
    public Pin drawBowl(Pin pin, int index) {
        if(pin.isAllClear()) {
            return new Pin(0,0);
        }
        return new Pin(pin.leftPins(), 5);
    }

}
