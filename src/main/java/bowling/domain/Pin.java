package bowling.domain;

public class Pin {
    private static final int BOWLING_PINS = 10;

    private int firstBall = 0;
    private int SecondBall = 0;

    public Pin(int firstBall) {
        this.firstBall = firstBall;
    }
}
