package bowling.model.frame.state;

import bowling.model.DoublePins;
import bowling.model.DownPin;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.DoublePins.FIRST;

public class Spare extends SecondState {

    private static final String SYMBOL = "/";

    Spare(DoublePins downDoublePins) {
        super(downDoublePins);
    }

    public static State valueOf(DownPin first) {
        return new Spare(DoublePins.valueOf(first));
    }

    @Override
    public Score getScore() {
        return Score.SPARE;
    }

    @Override
    public String printResult() {
        if (Gutter.isMatch(getDoublePins().get(FIRST))) {
            return Pretty.putPartitionOfState(Gutter.SYMBOL, SYMBOL);
        }
        return Pretty.putPartitionOfState(getDoublePins().get(FIRST).toString(), SYMBOL);
    }
}
