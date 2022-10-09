package bowling.step2.domain.state;

import bowling.step2.domain.Score;

public class Ready extends Running {
    @Override
    public State bowl(final int fallenPins) {
        if (fallenPins == COUNT_OF_MAX_PINS) {
            return new Strike();
        }
        
        return new Normal(new Score(fallenPins));
    }
    
    @Override
    public String display() {
        return "";
    }
}
