package bowling.domain.bowl;

import bowling.domain.pin.Pin;

public class FirstBowl extends ProceedingBowl {

    private static final BowlType BOWL_TYPE = BowlType.FIRST;

    @Override
    public Bowl pitch(Pin pin) {
        if (pin.isAllHit()) {
            return StrikeBowl.bowl();
        }
        return new NextBowl(pin);
    }

    @Override
    public boolean typeEquals(BowlType bowlType) {
        return BOWL_TYPE.equals(bowlType);
    }


}
