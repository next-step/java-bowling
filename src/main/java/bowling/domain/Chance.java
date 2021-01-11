package bowling.domain;

import bowling.util.ErrorMessage;

import java.util.Random;

public class Chance {
    private static Random random = new Random();

    private static int MAX_PINS_COUNT = 10;
    private static int MIN_PINS_COUNT = 0;

    private int countOfPin = 0;

    public Chance() {
        this.countOfPin = random.nextInt(MAX_PINS_COUNT + 1);
    }

    public Chance(int countOfPin) {
        checkPinCountRange(countOfPin);
        this.countOfPin = countOfPin;
    }

    public static Chance nextRollTheBall(Chance chance) {
        int nextCountOfPin = random.nextInt(MAX_PINS_COUNT - chance.countOfPin + 1);

        return new Chance(nextCountOfPin);
    }

    private void checkPinCountRange(final int countOfPin) {
        if (!(countOfPin >= MIN_PINS_COUNT) || !(countOfPin <= MAX_PINS_COUNT)) {
            throw new RuntimeException(ErrorMessage.getCheckPinCount());
        }
    }

    public int getCountOfPin() {
        return countOfPin;
    }
}
