package bowling.strategy;

import bowling.domain.Pin;

import java.util.Random;

public class RandomBowlingStrategy implements BowlingStrategy {

    Random random = new Random();

    @Override
    public Pin drawBowl(Pin pin, int index) {
        if(pin.isAllClear()) {
            return new Pin(0,0);
        }
        return new Pin(pin.leftPins(), random.nextInt(pin.leftPins() + 1));
    }

}
