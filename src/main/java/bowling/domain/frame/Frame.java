package bowling.domain.frame;

import java.util.Arrays;

public class Frame {
    private final int[] pins = new int[2];

    public Frame(int firstBall, int secondBall) {
        this.pins[0] = firstBall;
        this.pins[1] = secondBall;
    }

    public int score() {
        return Arrays.stream(pins).sum();
    }
}
