package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.state.end.ResultState;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.first.Gutter;
import bowling.domain.state.end.first.HitNumber;

public class StateFactory {

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
