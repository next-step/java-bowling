package bowling.strategy;

import bowling.domain.Pin;

public interface BowlingStrategy {

    Pin drawBowl(Pin pin, int index);
    
}
