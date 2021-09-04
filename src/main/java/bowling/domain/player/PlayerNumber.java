package bowling.domain.player;

import bowling.exception.IllegalPayerNumberException;

public class PlayerNumber {

    private final int number;

    private PlayerNumber(int number) {
        validate(number);
        this.number = number;
    }

    public static PlayerNumber of(int number) {
        return new PlayerNumber(number);
    }

    private void validate(int number) {
        if (number < 0) {
            throw new IllegalPayerNumberException(number);
        }
    }

}
