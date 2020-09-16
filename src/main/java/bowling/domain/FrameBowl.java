package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;
import static bowling.domain.NumberOfPins.MIN_NUMBER_OF_PINS;

public class FrameBowl {

    private final List<NumberOfPins> numberOfPins = new ArrayList<>();

    public FrameBowl() {

    }

    public FrameBowl(int firstNumberOfPins) {
        bowl(firstNumberOfPins);
    }

    public FrameBowl(int firstNumberOfPins, int secondNumberOfPins) {
        bowl(firstNumberOfPins);
        bowl(secondNumberOfPins);
    }

    public void bowl(int numberOfPins) {
        this.numberOfPins.add(new NumberOfPins(numberOfPins));
    }

    public boolean isStrike() {
        return getBowlCount() == 1 && getTotalNumberOfPins() == MAX_NUMBER_OF_PINS;
    }

    public boolean isSpare() {
        return getBowlCount() == 2 && getTotalNumberOfPins() == MAX_NUMBER_OF_PINS;
    }

    public boolean isMiss() {
        int totalNumberOfPins = getTotalNumberOfPins();
        return getBowlCount() == 2 && (totalNumberOfPins > MIN_NUMBER_OF_PINS && totalNumberOfPins < MAX_NUMBER_OF_PINS);
    }

    public boolean isGutter() {
        return getBowlCount() == 2 && getTotalNumberOfPins() == MIN_NUMBER_OF_PINS;
    }

    public int getBowlCount() {
        return numberOfPins.size();
    }

    public int getTotalNumberOfPins() {
        return numberOfPins.stream()
                .map(NumberOfPins::getNumberOfPins)
                .reduce(0, Integer::sum);
    }

}
