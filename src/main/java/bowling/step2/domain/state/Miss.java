package bowling.step2.domain.state;

import bowling.step2.domain.Score;

import java.util.Objects;

public class Miss extends Finished{
    private final Score firstFallenPins;
    private final Score secondFallenPins;
    
    public Miss(final Score firstFallenPins, final Score secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Miss miss = (Miss) o;
        return Objects.equals(firstFallenPins, miss.firstFallenPins) && Objects.equals(secondFallenPins, miss.secondFallenPins);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPins, secondFallenPins);
    }
}
