package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.DOWN_ZERO;

public class Gutter implements State {

    static final String PRINT_SYMBOL_OF_GUTTER = "-";
    private static final Pins first = DOWN_ZERO;

    static State valueOf() {
        return new Gutter();
    }

    static boolean isMatch(Pins downPins) {
        return first.equals(downPins);
    }

    public State bowl(Pins second) {
        if (second.equals(Pins.DOWN_ALL)) {
            return Spare.valueOf(first);
        }
        return Miss.valueOf(first, second);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String printResult() {
        return PRINT_SYMBOL_OF_GUTTER;
    }

}
