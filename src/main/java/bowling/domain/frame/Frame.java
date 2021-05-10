package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;

public abstract class Frame {

    public static final int START_SEQUENCE = 1;
    public static final int LAST_SEQUENCE = 10;

    public abstract Frame bowl(Pins fallPins);

    public abstract boolean isFinish();

    public abstract Score score();

    public abstract int sequence();
}
