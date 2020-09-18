package bowling.domain.bowl;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class BowlValidator {

    public static final int MAX_BOWL_COUNT = 2;

    private BowlValidator() {

    }

    public static void validateBowlCount(int bowlCount) {
        if (bowlCount >= MAX_BOWL_COUNT) {
            throw new IllegalArgumentException("2번까지만 투구할 수 있습니다.");
        }
    }

    public static void validateTotalNumberOfPins(int totalNumberOfPins, int numberOfPins) {
        if (totalNumberOfPins + numberOfPins > MAX_NUMBER_OF_PIN) {
            throw new IllegalArgumentException("핀 갯수가 10개를 초과했습니다.");
        }
    }

}
