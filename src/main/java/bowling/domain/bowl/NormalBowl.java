package bowling.domain.bowl;

import bowling.domain.NumberOfPin;

import java.util.ArrayList;
import java.util.List;

public class NormalBowl {

    public static final int FIRST_NUMBER_OF_PINS_INDEX = 0;
    public static final int SECOND_NUMBER_OF_PINS_INDEX = 1;

    private final List<NumberOfPin> numberOfPins = new ArrayList<>();

    private NormalBowlResult normalBowlResult = NormalBowlResult.NONE;

    public NormalBowl() {

    }

    public NormalBowl(int firstNumberOfPins) {
        bowl(firstNumberOfPins);
    }

    public NormalBowl(int firstNumberOfPins, int secondNumberOfPins) {
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
        normalBowlResult = NormalBowlResult.getType(this);
    }

    public boolean isNone() {
        return numberOfPins.isEmpty();
    }

    public boolean isCompleted() {
        return normalBowlResult.isCompleted();
    }

    public boolean isBonus() {
        return normalBowlResult.isBonus();
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
        return normalBowlResult.format(this);
    }

}
