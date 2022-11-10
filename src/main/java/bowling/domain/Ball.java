package bowling.domain;

import java.util.Random;

public class Ball {
    private static final Random RANDOM = new Random();
    private int knockedDownPinCount;

    public Ball() {
        this(0);
    }

    public Ball(int knockedDownPinCount) {
        this.knockedDownPinCount = knockedDownPinCount;
    }

    public int pitch(Pins pins){
        int knockedDownPinCount = RANDOM.nextInt(pins.standingPinCount() + 1);
        return this.knockedDownPinCount = pins.knockDown(knockedDownPinCount);
    }

    public int getKnockedDownPinCount() {
        return knockedDownPinCount;
    }
}
