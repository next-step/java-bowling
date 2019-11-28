package bowling.domain.state;

import bowling.domain.Pin;

import java.util.Arrays;
import java.util.List;

public class Miss extends EndState {
    public Miss(int first, int second) {
        this(Arrays.asList(Pin.of(first), Pin.of(second)));
    }

    public Miss(List<Pin> pins) {
        this.pins = pins;
    }
}
