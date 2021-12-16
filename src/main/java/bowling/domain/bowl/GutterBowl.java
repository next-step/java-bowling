package bowling.domain.bowl;

import bowling.domain.pin.Pin;

public class GutterBowl extends FinishedBowl {

    private static final BowlType BOWL_TYPE = BowlType.GUTTER;
    private static final GutterBowl CACHED_BOWL = new GutterBowl();
    private static final int NON_HIT = 0;

    private final Pin firstPin = Pin.from(NON_HIT);
    private final Pin secondPin = Pin.from(NON_HIT);

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
    public String getView() {
        return " -|-  ";
    }
}
