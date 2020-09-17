package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;

public class NormalBowl {

    public static final int FIRST_NUMBER_OF_PINS_INDEX = 0;
    public static final int SECOND_NUMBER_OF_PINS_INDEX = 1;

    private final List<NumberOfPins> numberOfPins = new ArrayList<>();

    public NormalBowl() {

    }

    public NormalBowl(int firstNumberOfPins) {
        bowl(firstNumberOfPins);
    }

    public NormalBowl(int firstNumberOfPins, int secondNumberOfPins) {
        bowl(firstNumberOfPins);
        bowl(secondNumberOfPins);
    }

    public NormalBowlResult bowl(int numberOfPins) {
        validateBowlCount();
        validateTotalNumberOfPins(numberOfPins);
        addNumberOfPins(numberOfPins);
        return NormalBowlResult.getType(this);
    }

    private void addNumberOfPins(int numberOfPins) {
        this.numberOfPins.add(new NumberOfPins(numberOfPins));
    }

    private void validateBowlCount() {
        if (getBowlCount() >= 2) {
            throw new IllegalArgumentException("2번까지만 투구할 수 있습니다.");
        }
    }

    private void validateTotalNumberOfPins(int numberOfPins) {
        if (getTotalNumberOfPins() + numberOfPins > MAX_NUMBER_OF_PINS) {
            throw new IllegalArgumentException("핀 갯수가 10개를 초과했습니다.");
        }
    }

    public boolean isNone() {
        return numberOfPins.isEmpty();
    }

    public int getFirstNumberOfPins() {
        return numberOfPins.get(FIRST_NUMBER_OF_PINS_INDEX).getNumberOfPins();
    }

    public int getSecondNumberOfPins() {
        return numberOfPins.get(SECOND_NUMBER_OF_PINS_INDEX).getNumberOfPins();
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
