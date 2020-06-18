package bowling.step4.domain;

import bowling.step4.exception.PlayerMinimumCountException;

import java.util.stream.Stream;

public class PlayerCount {

    private final int count;

    private PlayerCount(int count) {
        this.count = count;
    }

    public static PlayerCount of(int count) {
        return new PlayerCount(count);
    }

    private static void validate(int count) {
        if (count < 0) {
            throw new PlayerMinimumCountException();
        }
    }

}