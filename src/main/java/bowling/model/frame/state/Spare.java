package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.frame.state.Gutter.PRINT_SYMBOL_OF_GUTTER;

public class Spare implements State {

    private static final String SYMBOL_OF_SPARE = "/";
    private Pins firstBowl;

    private Spare(Pins firstBowl) {
        this.firstBowl = firstBowl;
    }

    public static State valueOf(Pins firstBowl) {
        return new Spare(firstBowl);
    }

    @Override
    public State bowl(Pins pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String printResult() {
        if (Gutter.isMatch(firstBowl)) {
            return Pretty.putPartitionOfState(PRINT_SYMBOL_OF_GUTTER, SYMBOL_OF_SPARE);
        }
        return Pretty.putPartitionOfState(firstBowl.toString(), SYMBOL_OF_SPARE);
    }
}
