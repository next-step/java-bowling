package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import java.util.Arrays;
import java.util.List;

public class Spare extends SecondState {

    private static final String SYMBOL = "/";

    private Spare(List<DownPin> downDoublePins) {
        super(downDoublePins);
    }

    public static State valueOf(DownPin first) {
        return new Spare(Arrays.asList(first, first.saveRemaining()));
    }

    @Override
    public Score getScore() {
        return Score.SPARE;
    }

    @Override
    public String printResult() {
        return Pretty.putPartitionOfState(getFirstSymbol(), SYMBOL);
    }
}
