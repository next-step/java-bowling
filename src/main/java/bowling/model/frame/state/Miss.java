package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import java.util.Arrays;
import java.util.List;

import static bowling.model.DownPin.DOWN_ZERO;

public class Miss extends SecondState {

    private Miss(List<DownPin> doublePins) {
        super(doublePins);
    }

    static State valueOf(DownPin first, DownPin second) {
        return new Miss(Arrays.asList(first, second));
    }

    @Override
    public Score getScore() {
        int countOfPins = getDoublePins().stream()
                .reduce(DOWN_ZERO, DownPin::sum)
                .count();
        return Score.of(countOfPins);
    }

    @Override
    public String printResult() {
        return Pretty.putPartitionOfState(
                getFirstSymbol(),
                getSecondDownPins().toString());
    }
}