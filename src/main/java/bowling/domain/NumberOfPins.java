package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NumberOfPins {

    public static final int FIRST_NUMBER_OF_PINS_INDEX = 0;
    public static final int SECOND_NUMBER_OF_PINS_INDEX = 1;

    private final List<NumberOfPin> numberOfPins = new ArrayList<>();

    public void addNumberOfPins(int numberOfPin) {
        numberOfPins.add(new NumberOfPin(numberOfPin));
    }

    public boolean isNone() {
        return numberOfPins.isEmpty();
    }

    public int getFirstNumberOfPins() {
        return numberOfPins.get(FIRST_NUMBER_OF_PINS_INDEX).getNumberOfPin();
    }

    public int getSecondNumberOfPins() {
        return numberOfPins.get(SECOND_NUMBER_OF_PINS_INDEX).getNumberOfPin();
    }

    public int getBowlCount() {
        return numberOfPins.size();
    }

    public int getTotalNumberOfPin() {
        return numberOfPins.stream()
                .map(NumberOfPin::getNumberOfPin)
                .reduce(0, Integer::sum);
    }

}
