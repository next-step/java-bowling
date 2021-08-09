package bowling.domain.state.result;

import bowling.domain.pin.Pins;
import bowling.domain.state.CommonState;

public abstract class End implements CommonState {

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public CommonState hitPins(Pins pins) {
        throw new IllegalStateException("다음은 없습니다");
    }

}
