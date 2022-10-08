package bowling.step2.domain.state;

import bowling.step2.domain.Score;

import java.util.Objects;

public class Normal extends Running {
    private final Score firstFallenPins;
    
    public Normal(final Score firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
    }
    
    @Override
    public State bowl(final int secondFallenPins) {
        if (isAllFallenPins(secondFallenPins)) {
            return new Spare(firstFallenPins);
        }
        
        return new Miss(firstFallenPins, new Score(secondFallenPins));
    }
    
    private boolean isAllFallenPins(final int secondFallenPins) {
        return firstFallenPins.isAllFallenPins(secondFallenPins);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Normal normal = (Normal) o;
        return Objects.equals(firstFallenPins, normal.firstFallenPins);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPins);
    }
}
