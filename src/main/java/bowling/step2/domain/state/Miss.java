package bowling.step2.domain.state;

import bowling.step2.domain.Score;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Miss extends Finished {
    private final Score firstFallenPins;
    private final Score secondFallenPins;
    
    public Miss(final Score firstFallenPins, final Score secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
    }
    
    @Override
    public List<Score> getScores() {
        return Collections.unmodifiableList(Arrays.asList(firstFallenPins, secondFallenPins));
    }
}
