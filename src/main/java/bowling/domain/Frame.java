package bowling.domain;

public class Frame {
    private static final int END_TURN = 2;
    private final Pin pin;
    private int turn = 0;

    private Frame(Pin pin) {
        turn++;
        this.pin = pin;
    }

    public static Frame first(int countOfDownPin) {
        return new Frame(Pin.first(countOfDownPin));
    }

    public Frame second(int countOfDownPin) {
        turn++;
        return new Frame(pin.next(countOfDownPin));
    }
    
    public boolean isEnd() {
        return turn >= END_TURN;
    }
}
