package bowling.domain.frame;

import bowling.domain.value.Pins;

public abstract class Frame {

    public abstract boolean isFrameOver();
    public abstract boolean isGameOver();

    public abstract void bowl(Pins pins);

    public abstract String getMark();
}
