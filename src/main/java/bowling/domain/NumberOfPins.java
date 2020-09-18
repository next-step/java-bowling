package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class NumberOfPins {

    public static final int FIRST_NUMBER_OF_PINS_INDEX = 0;
    public static final int SECOND_NUMBER_OF_PINS_INDEX = 1;
    public static final int MAX_BOWL_COUNT = 2;

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

    public void validateBowlCount() {
        if (getBowlCount() >= MAX_BOWL_COUNT) {
            throw new IllegalArgumentException("2번까지만 투구할 수 있습니다.");
        }
    }

    public void validateTotalNumberOfPins(int numberOfPin) {
        if (getTotalNumberOfPin() + numberOfPin > MAX_NUMBER_OF_PIN) {
            throw new IllegalArgumentException("핀 갯수가 10개를 초과했습니다.");
        }
    }

}
