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

    public Bowl(int firstNumberOfPins) {
        bowl(firstNumberOfPins);
    }

    public Bowl(int firstNumberOfPins, int secondNumberOfPins) {
        bowl(firstNumberOfPins);
        bowl(secondNumberOfPins);
    }

    public void bowl(int numberOfPins) {
        BowlValidator.validateBowlCount(getBowlCount());
        BowlValidator.validateTotalNumberOfPins(getTotalNumberOfPins(), numberOfPins);
        addNumberOfPins(numberOfPins);
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

    public int getTotalNumberOfPins() {
        return numberOfPins.stream()
                .map(NumberOfPin::getNumberOfPin)
                .reduce(0, Integer::sum);
    }

    public String format() {
        return bowlStatus.format(this);
    }

}
