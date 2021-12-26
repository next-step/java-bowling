package bowling.domain.result;

import bowling.domain.Pin;
import bowling.domain.result.status.Miss;
import bowling.domain.result.status.PinResultState;
import bowling.domain.result.status.Spare;
import bowling.domain.result.status.Strike;
import bowling.domain.result.status.Gutter;
import bowling.domain.result.status.HitNumber;

public final class StateFactory {

    private StateFactory() {
    }

    public static PinResultState strike(Pin pin) {
        return new Strike(pin);
    }

    public static PinResultState spare(Pin pin) {
        return new Spare(pin);
    }

    public static PinResultState gutter(Pin pin) {
        return new Gutter(pin);
    }

    public static PinResultState miss(Pin pin) {
        return new Miss(pin);
    }

    public static PinResultState number(Pin pin) {
        return new HitNumber(pin);
    }


}
