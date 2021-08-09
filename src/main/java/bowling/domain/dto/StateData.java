package bowling.domain.dto;

import bowling.domain.state.CommonState;

import java.util.List;

public class StateData {
    private static final int FIRST_PITCHING_INDEX = 0;
    private static final int SECOND_PITCHING_INDEX = 1;

    private final String type;
    private final List<Integer> hitPins;

    private StateData(CommonState state) {
        this.type = state.getClass().getSimpleName();
        this.hitPins = state.getHitPins();
    }

    public static StateData of(CommonState state) {
        return new StateData(state);
    }

    public String getType() {
        return type;
    }

    public int getCountFirstPins() {
        return hitPins.get(FIRST_PITCHING_INDEX);
    }

    public int getCountSecondPins() {
        return hitPins.get(SECOND_PITCHING_INDEX);
    }

}
