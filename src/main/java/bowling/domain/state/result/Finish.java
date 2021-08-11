package bowling.domain.state.result;

import bowling.domain.state.CommonState;
import bowling.domain.state.BunchState;

import java.util.Collections;
import java.util.List;

public class Finish extends End {

    private final BunchState bunchState;

    public Finish(BunchState bunchState) {
        this.bunchState = bunchState;
    }

    public static Finish of(BunchState bunchState) {
        return new Finish(bunchState);
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.emptyList();
    }

    @Override
    public List<CommonState> getState() {
        return bunchState.getState();
    }

}
