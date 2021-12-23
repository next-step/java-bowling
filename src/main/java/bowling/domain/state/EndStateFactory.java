package bowling.domain.state;

import bowling.domain.state.end.EndState;
import bowling.domain.state.end.Gutter;
import bowling.domain.state.end.Strike;

public class EndStateFactory {

    public static EndState strike() {
        return new Strike();
    }


    public static EndState gutter() {
        return new Gutter();
    }
}
