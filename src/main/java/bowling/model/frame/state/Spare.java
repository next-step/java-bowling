package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.frame.state.Gutter.PRINT_SYMBOL_OF_GUTTER;

public class Spare extends SecondState {

    private static final String SYMBOL_OF_SPARE = "/";

    private Spare(Pins firstBowl, Pins secondBowl) {
        super(firstBowl, secondBowl);
    }

    public static State valueOf(Pins firstBowl) {
        return new Spare(firstBowl, firstBowl.saveRemaining());
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public String printResult() {
        if (Gutter.isMatch(getFirstBowl())) {
            return Pretty.putPartitionOfState(PRINT_SYMBOL_OF_GUTTER, SYMBOL_OF_SPARE);
        }
        return Pretty.putPartitionOfState(getFirstBowl().toString(), SYMBOL_OF_SPARE);
    }
}
