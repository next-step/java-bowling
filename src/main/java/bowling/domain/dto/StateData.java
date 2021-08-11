package bowling.domain.dto;

import bowling.domain.state.CommonState;

import java.util.List;
import java.util.stream.Collectors;

public class StateData {
    private final List<StateDatum> stateData;

    private StateData(List<CommonState> states) {
        stateData = states.stream()
                .map(StateDatum::of)
                .collect(Collectors.toList());
    }

    public static StateData of(List<CommonState> states) {
        return new StateData(states);
    }

    public List<StateDatum> getStateData() {
        return stateData;
    }
}
