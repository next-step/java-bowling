package bowling.domain;

public class Frame {
    private static final int END_TURN = 2;
    private static final int FIRST_TURN = 1;
    private static final boolean IS_FIRST = true;

    private final Pin pin;
    private final int turn;

    private Frame(Pin pin, int turn) {
        this.turn = turn;
        this.pin = pin;
    }

    public static Frame first(int countOfDownPin) {
        Pin pin = Pin.first(countOfDownPin);
        int turn = checkStrike(pin);
        return new Frame(pin, turn);
    }

    private static int checkStrike(Pin pin) {
        if (Rule.of(pin, IS_FIRST) == Rule.STRIKE) {
            return END_TURN;
        }
        return FIRST_TURN;
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
