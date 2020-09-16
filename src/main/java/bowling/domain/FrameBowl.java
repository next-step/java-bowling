package bowling.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;
import static bowling.domain.NumberOfPins.MIN_NUMBER_OF_PINS;

public class FrameBowl {

    public static final int FIRST_BOWL = 1;
    public static final int SECOND_BOWL = 2;

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

    public BowlResult bowl(int numberOfPins) {
        validateBowlCount();
        validateTotalNumberOfPins(numberOfPins);
        this.numberOfPins.add(new NumberOfPins(numberOfPins));
        return BowlResult.getType(this);
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

}
