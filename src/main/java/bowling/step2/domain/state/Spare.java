package bowling.step2.domain.state;

import bowling.step2.domain.Score;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Spare extends Finished {
    private final Score firstFallenPins;
    private final Score secondFallenPins;
    
    public Spare(final Score firstFallenPins, final Score secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
    }
    
    @Override
    public boolean isSpare() {
        return true;
    }
    
    @Override
    public List<Score> getScores() {
        return Collections.unmodifiableList(Arrays.asList(firstFallenPins, secondFallenPins));
    }
}
