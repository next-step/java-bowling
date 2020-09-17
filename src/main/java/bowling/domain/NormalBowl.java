package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;
import static bowling.domain.NumberOfPins.MIN_NUMBER_OF_PINS;

public class NormalBowl {

    protected final List<NumberOfPins> numberOfPins = new ArrayList<>();

    public static final int FIRST_BOWL = 1;
    public static final int SECOND_BOWL = 2;

    public NormalBowl() {

    }

    public NormalBowl(int firstNumberOfPins) {
        bowl(firstNumberOfPins);
    }

    public NormalBowl(int firstNumberOfPins, int secondNumberOfPins) {
        bowl(firstNumberOfPins);
        bowl(secondNumberOfPins);
    }

    public void addNumberOfPins(int numberOfPins) {
        this.numberOfPins.add(new NumberOfPins(numberOfPins));
    }

    public NormalBowlResult bowl(int numberOfPins) {
        validateBowlCount();
        validateTotalNumberOfPins(numberOfPins);
        addNumberOfPins(numberOfPins);
        return NormalBowlResult.getType(this);
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

    public int getBowlCount() {
        return numberOfPins.size();
    }

    public int getTotalNumberOfPins() {
        return numberOfPins.stream()
                .map(NumberOfPins::getNumberOfPins)
                .reduce(0, Integer::sum);
    }

    public int getFirstNumberOfPins() {
        return numberOfPins.get(0).getNumberOfPins();
    }

    public int getSecondNumberOfPins() {
        return numberOfPins.get(1).getNumberOfPins();
    }

}
