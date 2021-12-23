package bowling.state;

import bowling.state.State;
import bowling.state.end.EndState;
import bowling.state.end.Gutter;
import bowling.state.end.Miss;
import bowling.state.end.Spare;
import bowling.state.end.Strike;

public class EndStateFactory {

    public static EndState strike() {
        return new Strike();
    }

    public static EndState spare() {
        return new Spare();
    }

    public static EndState miss() {
        return new Miss();
    }

    public static EndState gutter() {
        return new Gutter();
    }
}
