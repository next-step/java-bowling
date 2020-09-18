package bowling.domain.bowl;

import bowling.domain.NumberOfPin;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class Bowl {

    public static final int MAX_BOWL_COUNT = 2;

    private final List<NumberOfPin> numberOfPins = new ArrayList<>();

    public Bowl() {

    }

    public Bowl(int firstNumberOfPin) {
        bowl(firstNumberOfPin);
    }

    public Bowl(int firstNumberOfPin, int secondNumberOfPin) {
        bowl(firstNumberOfPin);
        bowl(secondNumberOfPin);
    }

    public BowlResult bowl(int numberOfPin) {
        validateBowlCount();
        validateTotalNumberOfPins(numberOfPin);
        numberOfPins.add(new NumberOfPin(numberOfPin));
        return new BowlResult(numberOfPins);
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

    public int getBowlCount() {
        return numberOfPins.size();
    }

    public int getTotalNumberOfPin() {
        return numberOfPins.stream()
                .map(NumberOfPin::getNumberOfPin)
                .reduce(0, Integer::sum);
    }

}
