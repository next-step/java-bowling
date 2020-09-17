package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;
import static bowling.domain.NumberOfPins.MIN_NUMBER_OF_PINS;

public abstract class AbstractBowl implements Bowl {

    protected final List<NumberOfPins> numberOfPins = new ArrayList<>();

    public static final int FIRST_BOWL = 1;
    public static final int SECOND_BOWL = 2;

    public void addNumberOfPins(int numberOfPins) {
        this.numberOfPins.add(new NumberOfPins(numberOfPins));
    }

    public boolean isStrike() {
        return getBowlCount() == FIRST_BOWL && getTotalNumberOfPins() == MAX_NUMBER_OF_PINS;
    }

    public boolean isSpare() {
        return getBowlCount() == SECOND_BOWL && getTotalNumberOfPins() == MAX_NUMBER_OF_PINS;
    }

    public boolean isMiss() {
        int totalNumberOfPins = getTotalNumberOfPins();
        return getBowlCount() == SECOND_BOWL && (totalNumberOfPins > MIN_NUMBER_OF_PINS && totalNumberOfPins < MAX_NUMBER_OF_PINS);
    }

    public boolean isGutter() {
        return getBowlCount() == SECOND_BOWL && getTotalNumberOfPins() == MIN_NUMBER_OF_PINS;
    }

    @Override
    public int getBowlCount() {
        return numberOfPins.size();
    }

    @Override
    public int getTotalNumberOfPins() {
        return numberOfPins.stream()
                .map(NumberOfPins::getNumberOfPins)
                .reduce(0, Integer::sum);
    }

}
