package bowling.domain;

public class Frame {
    private static final int END_TURN = 2;
    private static final int FIRST_TURN = 1;

    private final Pin pin;
    private final int turn;

    private Frame(Pin pin, int turn) {
        this.turn = turn;
        this.pin = pin;
    }

    public static Frame first(int countOfDownPin) {
        return new Frame(Pin.first(countOfDownPin), FIRST_TURN);
    }

    public Frame second(int countOfDownPin) {
        return new Frame(pin.next(countOfDownPin), END_TURN);
    }

    public boolean isEnd() {
        return turn >= END_TURN;
    }

    public int firstDownPin() {
        return pin.firstBall();
    }

    public int secondDownPin() {
        return pin.secondBall();
    }
}
