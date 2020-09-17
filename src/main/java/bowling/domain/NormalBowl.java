package bowling.domain;

import static bowling.domain.NumberOfPins.MAX_NUMBER_OF_PINS;

public class NormalBowl extends AbstractBowl {

    public NormalBowl() {

    }

    public NormalBowl(int firstNumberOfPins) {
        bowl(firstNumberOfPins);
    }

    public NormalBowl(int firstNumberOfPins, int secondNumberOfPins) {
        bowl(firstNumberOfPins);
        bowl(secondNumberOfPins);
    }

    @Override
    public BowlResult bowl(int numberOfPins) {
        validateBowlCount();
        validateTotalNumberOfPins(numberOfPins);
        addNumberOfPins(numberOfPins);
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

}
