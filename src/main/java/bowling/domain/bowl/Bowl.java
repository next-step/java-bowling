package bowling.domain.bowl;

import bowling.domain.NumberOfPin;

import java.util.ArrayList;
import java.util.List;

public class Bowl {

    public static final int FIRST_NUMBER_OF_PINS_INDEX = 0;
    public static final int SECOND_NUMBER_OF_PINS_INDEX = 1;

    private final List<NumberOfPin> numberOfPins = new ArrayList<>();

    private BowlStatus bowlStatus = BowlStatus.NONE;

    public Bowl() {

    }

    public Bowl(int firstNumberOfPin) {
        bowl(firstNumberOfPin);
    }

    public Bowl(int firstNumberOfPin, int secondNumberOfPin) {
        bowl(firstNumberOfPin);
        bowl(secondNumberOfPin);
    }

    public void bowl(int numberOfPin) {
        BowlValidator.validateBowlCount(getBowlCount());
        BowlValidator.validateTotalNumberOfPins(getTotalNumberOfPin(), numberOfPin);
        addNumberOfPins(numberOfPin);
        updateNormalBowlResult();
    }

    private void addNumberOfPins(int numberOfPins) {
        this.numberOfPins.add(new NumberOfPin(numberOfPins));
    }

    private void updateNormalBowlResult() {
        bowlStatus = BowlStatus.getType(this);
    }

    public boolean isNone() {
        return numberOfPins.isEmpty();
    }

    public boolean isCompleted() {
        return bowlStatus.isCompleted();
    }

    public boolean isBonus() {
        return bowlStatus.isBonus();
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

    public String format() {
        return bowlStatus.format(this);
    }

}
