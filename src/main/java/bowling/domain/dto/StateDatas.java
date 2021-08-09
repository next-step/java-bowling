package bowling.domain.dto;

import bowling.domain.state.CommonState;

import java.util.List;
import java.util.stream.Collectors;

public class StateDatas {
    private final List<StateData> stateDatas;

    private StateDatas(List<CommonState> states) {
        stateDatas = states.stream()
                .map(StateData::of)
                .collect(Collectors.toList());
    }

    public static StateDatas of(List<CommonState> states) {
        return new StateDatas(states);
    }

    public List<StateData> getStateDatas() {
        return stateDatas;
    }
}
