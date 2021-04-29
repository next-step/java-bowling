package bowling_refactor.domain.state.complete_state;

import bowling_refactor.domain.Pin;

import java.util.Arrays;
import java.util.List;

public class Last extends Complete {

    public Last(int first, int second, int third) {
        this(Arrays.asList(Pin.of(first), Pin.of(second), Pin.of(third)));
    }

    public Last(List<Pin> pins) {
        this.pins = pins;
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
