package bowling.domain.state;

import bowling.Pin;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.first.Gutter;
import bowling.domain.state.end.first.HitNumber;

public class StateFactory {

    public static EndState strike(Pin pin) {
        return new Strike(pin);
    }

    public static EndState spare(Pin pin) {
        return new Spare(pin);
    }

    public static EndState gutter(Pin pin) {
        return new Gutter(pin);
    }

    public static EndState miss() {
        return new Miss();
    }

    public static EndState number(Pin pin) {
        return new HitNumber(pin);
    }


}
