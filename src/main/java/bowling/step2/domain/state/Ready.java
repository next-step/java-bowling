package bowling.step2.domain.state;

import bowling.step2.domain.Score;

import java.util.Collections;
import java.util.List;

public class Ready extends Running {
    private static final int READY_SCORE = -1;
    
    private final Score firstFallenPins;
    
    public Ready() {
        this.firstFallenPins = new Score(READY_SCORE);
    }
    
    @Override
    public State bowl(final int fallenPins) {
        if (fallenPins == COUNT_OF_MAX_PINS) {
            return new Strike();
        }
        
        return new Normal(new Score(fallenPins));
    }
    
    @Override
    public List<Score> getScores() {
        return Collections.singletonList(firstFallenPins);
    }
}
