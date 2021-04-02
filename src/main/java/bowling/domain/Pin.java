package bowling.domain;

import java.util.function.BiPredicate;

public class Pin {
    private static final String MAX_OVER_PINS = "넘어뜨리는 볼링핀은 10개가 최대입니다.";
    private static final String MIN_UNDER_PINS = "넘어뜨리는 볼링핀은 0개 미만이 안됩니다.";
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private int firstBall = 0;
    private int secondBall = 0;

    private Pin(int firstBall) {
        this(firstBall, 0);
    }

    private Pin(int firstBall, int secondBall) {
        validMaxPin(firstBall, secondBall);
        validMinPin(firstBall, secondBall);
        this.firstBall = firstBall;
        this.secondBall = secondBall;
    }

    public static Pin first(int firstBall) {
        return new Pin(firstBall);
    }

    public Pin next(int secondBall) {
        return new Pin(this.firstBall, secondBall);
    }

    private void validMinPin(int firstBall, int secondBall) {
        if (firstBall < MIN_PINS
                || secondBall < MIN_PINS) {
            throw new IllegalArgumentException(MIN_UNDER_PINS);
        }
    }

    private void validMaxPin(int firstBall, int secondBall) {
        if ((firstBall + secondBall) > MAX_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    public boolean checkOfDownPin(BiPredicate<Integer, Integer> knockOverCount) {
        return knockOverCount.test(firstBall, secondBall);
    }
}


