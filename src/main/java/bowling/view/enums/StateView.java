package bowling.view.enums;

import bowling.domain.state.*;
import bowling.view.strategy.BasicStateToViewConvertStrategy;
import bowling.view.strategy.SpareStateToViewConvertStrategy;
import bowling.view.strategy.StateToViewConvertStrategy;

import java.util.Arrays;

public enum StateView {

    READY_STATE(Ready.class, new BasicStateToViewConvertStrategy()),
    FIRST_BOWL_STATE(FirstBowl.class, new BasicStateToViewConvertStrategy()),
    MISS_STATE(Miss.class, new BasicStateToViewConvertStrategy()),
    STRIKE_STATE(Strike.class, new BasicStateToViewConvertStrategy()),
    SPARE_STATE(Spare.class, new SpareStateToViewConvertStrategy());


    private final Class clazz;
    private final StateToViewConvertStrategy stateToViewConvertStrategy;

    StateView(Class clazz, StateToViewConvertStrategy stateToViewConvertStrategy) {
        this.clazz = clazz;
        this.stateToViewConvertStrategy = stateToViewConvertStrategy;
    }

    public static StateView valueOf(State state) {
        return Arrays.stream(values())
                .filter(view -> state.getClass().equals(view.clazz))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String convert(State state) {
        return stateToViewConvertStrategy.convert(state);
    }
}
