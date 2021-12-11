package bowling.domain.bowl;

import bowling.domain.pin.Pin;

public class FirstBowl extends ProceedingBowl {
    @Override
    public Bowl pitch(Pin pin) {
        if (pin.isAllHit()) {
            return new StrikeBowl();
        }
        return new NextBowl(pin);
    }
}
