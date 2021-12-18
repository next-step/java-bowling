package bowling.domain.bowl;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.List;

import static java.util.Collections.singletonList;

public class StrikeBowl extends FinishedBowl {

    private static final BowlType BOWL_TYPE = BowlType.STRIKE;
    private static final StrikeBowl CACHED_BOWL = new StrikeBowl();

    private final Pin pin;

    public StrikeBowl() {
        this(Pin.allHitPin());
    }

    private StrikeBowl(Pin pin) {
        this.pin = pin;
    }

    public static Bowl bowl() {
        return CACHED_BOWL;
    }

    @Override
    public Score score() {
        return Score.strike();
    }

    @Override
    public boolean typeEquals(BowlType bowlType) {
        return BOWL_TYPE.equals(bowlType);
    }

    @Override
    public List<Pin> pins() {
        return singletonList(pin);
    }
}
