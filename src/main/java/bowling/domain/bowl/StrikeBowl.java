package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.List;

import static java.util.Collections.emptyList;

public class StrikeBowl extends FinishedBowl {

    private static final BowlType BOWL_TYPE = BowlType.SPARE;
    private static final StrikeBowl CACHED_BOWL = new StrikeBowl();

    private StrikeBowl() {
    }

    public static Bowl bowl() {
        return CACHED_BOWL;
    }

    @Override
    public boolean typeEquals(BowlType bowlType) {
        return BOWL_TYPE.equals(bowlType);
    }

    @Override
    public List<Pin> pins() {
        return emptyList();
    }
}
