package bowling.step2.domain.state;

import bowling.step2.domain.Score;

import java.util.Collections;
import java.util.List;

public class Normal extends Running {
    private final Score firstFallenPins;
    
    public Normal(final Score firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
    }
    
    @Override
    public State bowl(final int secondFallenPins) {
        if (isAllFallenPins(secondFallenPins)) {
            return new Spare(firstFallenPins, new Score(secondFallenPins, true));
        }
        
        return new Miss(firstFallenPins, new Score(secondFallenPins));
    }
    
    private boolean isAllFallenPins(final int secondFallenPins) {
        return firstFallenPins.isAllFallenPins(secondFallenPins);
    }
    
    @Override
    public List<Score> getScores() {
        return Collections.singletonList(firstFallenPins);
    }
}
