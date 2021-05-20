package bowling.domain.pin;

public interface Pins {
    int NOT_PLAYED = 0;
    int FIRST = 0;
    int SECOND = 1;
    int THIRD = 2;
    int MAXIMUM_PINS = 10;

    void bowl(int pin);

    boolean isEnd();

    boolean isStrike();

    boolean isSpare();

    Pin pin(int index);

    int size();
}
