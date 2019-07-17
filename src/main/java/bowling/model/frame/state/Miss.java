package bowling.model.frame.state;

import bowling.model.DoublePins;
import bowling.model.DownPin;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.DoublePins.FIRST;
import static bowling.model.DoublePins.SECOND;
import static bowling.model.frame.state.Score.ZERO_OF_COUNT;

public class Miss extends SecondState {

    private Miss(DoublePins doublePins) {
        super(doublePins);
    }

    static State valueOf(DownPin first, DownPin second) {
        return new Miss(DoublePins.valueOf(first, second));
    }

    @Override
    public Score getScore() {
        return Score.of(ZERO_OF_COUNT, getDoublePins().getTotalCount());
    }

    @Override
    public String printResult() {
        return Pretty.putPartitionOfState(
                getDoublePins().get(FIRST).toString(),
                getDoublePins().get(SECOND).toString());
    }
}