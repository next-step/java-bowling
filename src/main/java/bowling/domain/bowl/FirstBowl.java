package bowling.domain.bowl;

import bowling.domain.pin.Pin;

public class FirstBowl extends ProceedingBowl {
    @Override
    public Bowl pitch(Pin pin) {
        if (pin.isAllHit()) {
            return StrikeBowl.bowl();
        }
        return new NextBowl(pin);
    }

    @Override
    public String getView() {
        return "      ";
    }
}
