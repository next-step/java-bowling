package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.List;

import static java.util.Collections.emptyList;

public class GutterBowl extends FinishedBowl {

    private static final BowlType BOWL_TYPE = BowlType.GUTTER;
    private static final GutterBowl CACHED_BOWL = new GutterBowl();

    private GutterBowl() {
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
