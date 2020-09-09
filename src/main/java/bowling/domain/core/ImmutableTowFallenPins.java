package bowling.domain.core;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

final class ImmutableTowFallenPins extends AbstractTowFallenPins {
    ImmutableTowFallenPins(List<Pins> towFallenPins) {
        super(Collections.unmodifiableList(towFallenPins));
    }

    ImmutableTowFallenPins(Pins f, Pins s) {
        this(asList(f,s));
    }

    boolean isGutter(int tryCount){
        return Pins.MIN_FALLEN_PIN_COUNT == getPins(tryCount).getFallenPins();
    }
}
