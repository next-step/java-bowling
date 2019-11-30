package bowling.domain.state;

import bowling.domain.Pin;

import java.util.Arrays;
import java.util.List;

public class Last extends EndState {
    public Last(int first, int second, int third) {
        this(Arrays.asList(Pin.of(first), Pin.of(second), Pin.of(third)));
    }

    public Last(List<Pin> pins) {
        this.pins = pins;
    }
}
