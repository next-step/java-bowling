package bowling.domain.state;

import bowling.Pin;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.first.Gutter;
import bowling.domain.state.end.first.HitNumber;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.domain.progress.FirstProgress;
import bowling.domain.progress.GeneralProgress;
import bowling.domain.progress.Progress;
import bowling.domain.progress.Closed;

public class StateFactory {

    public static EndState strike() {
        return new Strike();
    }

    public static EndState spare() {
        return new Spare();
    }

    public static EndState gutter() {
        return new Gutter();
    }

    public static EndState miss() {
        return new Miss();
    }

    public static EndState number(Pin pin) {
        return new HitNumber(pin);
    }


}
