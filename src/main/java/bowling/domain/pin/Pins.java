package bowling.domain.pin;

public abstract class Pins {
    public static final int NOT_PLAYED = 0;
    public static final int FIRST = 0;
    public static final int SECOND = 1;

    public abstract void bowl(int pin);

    public abstract boolean isStrike();
    public abstract boolean isSpare();
}
