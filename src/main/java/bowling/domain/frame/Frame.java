package bowling.domain.frame;

import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pins;

public abstract class Frame {

    public static final int STRIKE_BONUS = 2;
    public static final int SPARE_BONUS = 1;
    public static final int NO_BONUS = 0;

    public abstract void addPoint(int bonusPoint);

    public abstract void bowl(int pin);

    public abstract boolean isEnd();

    public abstract int bonusAmount();

    public abstract void endScoring();

    public abstract boolean endedScoring();

    public abstract Pins pins();
}
