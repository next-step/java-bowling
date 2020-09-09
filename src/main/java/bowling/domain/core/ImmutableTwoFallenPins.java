package bowling.domain.core;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

final class ImmutableTwoFallenPins extends AbstractTwoFallenPins {
    ImmutableTwoFallenPins(List<FallenPins> towFallenPins) {
        super(Collections.unmodifiableList(towFallenPins));
    }

    ImmutableTwoFallenPins(FallenPins f, FallenPins s) {
        this(asList(f,s));
    }

    boolean isGutter(int tryCount){
        return FallenPins.MIN_FALLEN_PIN_COUNT == getPins(tryCount).getPrimitive();
    }
}
