package bowling.domain.state;

import bowling.domain.pin.Pins;

import java.util.Collections;
import java.util.List;

public interface CommonState extends ResultState {

    CommonState hitPins(Pins pins);

    List<Integer> getHitPins();

    default List<CommonState> getState() {
        return Collections.singletonList(this);
    }

}
