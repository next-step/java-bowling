package bowling.domain.result;

import bowling.domain.Pin;
import bowling.domain.result.status.Miss;
import bowling.domain.result.status.Spare;
import bowling.domain.result.status.Strike;
import bowling.domain.result.status.Gutter;
import bowling.domain.result.status.HitNumber;

public final class StateFactory {

    private StateFactory() {

    }

    public static ResultState strike(Pin pin) {
        return new Strike(pin);
    }

    public static ResultState spare(Pin pin) {
        return new Spare(pin);
    }

    public static ResultState gutter(Pin pin) {
        return new Gutter(pin);
    }

    public static ResultState miss() {
        return new Miss();
    }

    public static ResultState number(Pin pin) {
        return new HitNumber(pin);
    }


}
