package bowling.domain;

import java.util.function.BiPredicate;

public class Pin {
    private static final int BOWLING_PINS = 10;

    private int firstBall = 0;
    private int secondBall = 0;

    public Pin(int firstBall) {
        this(firstBall, 0);
    }

    public Pin(int firstBall, int secondBall) {
        this.firstBall = firstBall;
        this.secondBall = secondBall;
    }

    public boolean checkOfPin(BiPredicate<Integer, Integer> knockOverCount) {
        return knockOverCount.test(firstBall, secondBall);
    }
}


