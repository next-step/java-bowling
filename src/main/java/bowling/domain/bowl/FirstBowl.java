package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Pin> pins() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "FirstBowl{}";
    }
}
