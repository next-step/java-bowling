package bowling.step2.domain.state;

import bowling.step2.domain.Score;

public class Ready implements State {
    @Override
    public State bowl(final int fallenPins) {
        if (fallenPins == 10) {
            return new Strike();
        }
        
        return new Normal(new Score(fallenPins));
    }
}
