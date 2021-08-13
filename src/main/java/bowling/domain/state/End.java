package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.state.CommonState;

public abstract class End extends CommonState {

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public CommonState hitPins(Pins pins) {
        throw new IllegalStateException("다음은 없습니다");
    }

}
