package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.frame.state.Gutter.PRINT_SYMBOL_OF_GUTTER;
import static bowling.model.frame.state.Score.SPARE;

public class Spare extends SecondState {

    private static final String SYMBOL_OF_SPARE = "/";

    private Spare(Pin firstBowl, Pin secondBowl) {
        super(firstBowl, secondBowl);
    }

    public static State valueOf(Pin firstBowl) {
        return new Spare(firstBowl, firstBowl.saveRemaining());
    }

    @Override
    public Score getScore() {
        return SPARE;
    }

    @Override
    public String printResult() {
        if (Gutter.isMatch(getFirstBowl())) {
            return Pretty.putPartitionOfState(PRINT_SYMBOL_OF_GUTTER, SYMBOL_OF_SPARE);
        }
        return Pretty.putPartitionOfState(getFirstBowl().toString(), SYMBOL_OF_SPARE);
    }
}
