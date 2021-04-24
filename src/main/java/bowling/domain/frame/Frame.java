package bowling.domain.frame;

import bowling.domain.Round;
import bowling.domain.Pin;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    public static final int FIRST_PLAY = 1;
    public static final int SECOND_PLAY = 2;
    public static final int BONUS_PLAY = 3;
    public static final int FIRST_PIN = 0;
    public static final int SECOND_PIN = 1;
    public static final int BONUS_PIN = 2;

    protected List<Pin> pins;

    public Frame() {
        pins = new ArrayList<>();
    }

    abstract public Frame next(Round round);

    abstract public void play(int countOfDownPin);

    public abstract boolean isEnd();

    public abstract Boolean isNextFrame();

    public int getPinsSize() {
        return pins.size();
    }

    public int getTotalPinCount() {
        return pins.stream()
                .map(Pin::getPin)
                .reduce(Pin.MIN_PIN_COUNT, Integer::sum);
    }

    public int getFirstPin() {
        return pins.get(FIRST_PIN).getPin();
    }

    public int getSecondPin() {
        return pins.get(SECOND_PIN).getPin();
    }

    public int getBonusPin() {
        return pins.get(BONUS_PIN).getPin();
    }

    public boolean isFirst() {
        return pins.size() == Frame.FIRST_PLAY;
    }

    public boolean isSecond() {
        return pins.size() == Frame.SECOND_PLAY;
    }

    public boolean isBonus() {
        return pins.size() == Frame.BONUS_PLAY;
    }
}
