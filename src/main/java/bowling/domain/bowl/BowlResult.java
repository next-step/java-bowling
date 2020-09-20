package bowling.domain.bowl;

import bowling.domain.NumberOfPin;
import bowling.domain.bowl.identity.BowlIdentity;
import bowling.domain.bowl.identity.BowlIdentityFinder;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class BowlResult {

    public static final int FIRST_NUMBER_OF_PINS_INDEX = 0;
    public static final int SECOND_NUMBER_OF_PINS_INDEX = 1;
    public static final int FIRST_BOWL_COUNT = 1;

    public final List<NumberOfPin> numberOfPins;

    public BowlResult() {
        this(Collections.EMPTY_LIST);
    }

    public BowlResult(List<NumberOfPin> numberOfPins) {
        this.numberOfPins = numberOfPins;
    }

    private BowlIdentity findBowlIdentity() {
        return BowlIdentityFinder.find(this);
    }

    public boolean isNone() {
        return numberOfPins.isEmpty();
    }

    public boolean isFirst() {
        return numberOfPins.size() == FIRST_BOWL_COUNT;
    }

    public boolean isCompleted() {
        return findBowlIdentity().isCompleted();
    }

    public boolean isBonus() {
        return findBowlIdentity().isBonus();
    }

    public int getFirstNumberOfPin() {
        return numberOfPins.get(FIRST_NUMBER_OF_PINS_INDEX).getNumberOfPin();
    }

    public int getSecondNumberOfPin() {
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

    public Score getScore(NormalFrame normalFrame) {
        return findBowlIdentity().getScore(normalFrame);
    }

    public String format() {
        return findBowlIdentity().format(this);
    }

    public boolean isStrike() {
        return findBowlIdentity().isStrike();
    }

    public boolean checkSpareBonus() {
        return !isNone();
    }

}
