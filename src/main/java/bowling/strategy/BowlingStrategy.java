package bowling.strategy;

import bowling.pin.domain.Pin;

public interface BowlingStrategy {

    Pin drawBowl(Pin pin, int index);
    
}
