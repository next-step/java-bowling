package bowling.view.dto;

import static java.util.stream.Collectors.*;

import java.util.List;

import bowling.domain.frame.state.State;

public class BowlRecordConverter {

    public static List<BowlRecord> convert(List<State> states) {
        return states.stream()
            .map(BowlRecordConverter::convert)
            .collect(toList());
    }

    public static BowlRecord convert(State state) {
        return new BowlRecord(state.getFalledPins(), state.isStrike(), state.isSpare());
    }
}
