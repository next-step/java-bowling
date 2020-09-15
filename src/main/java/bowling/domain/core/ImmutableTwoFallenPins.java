package bowling.domain.core;

import java.util.Collections;
import java.util.List;

import static bowling.domain.core.FallenPins.ten;
import static bowling.domain.core.FallenPins.zero;
import static java.util.Arrays.asList;

final class ImmutableTwoFallenPins extends AbstractTwoFallenPins {
    private static final ImmutableTwoFallenPins gutter = new ImmutableTwoFallenPins(zero(), zero());
    private static final ImmutableTwoFallenPins strike = new ImmutableTwoFallenPins(ten(), zero());

    ImmutableTwoFallenPins(List<FallenPins> twoFallenPins) {
        super(Collections.unmodifiableList(twoFallenPins));
    }

    ImmutableTwoFallenPins(FallenPins f, FallenPins s) {
        this(asList(f,s));
    }

    static ImmutableTwoFallenPins of(FallenPins f, FallenPins s){
        return new ImmutableTwoFallenPins(f, s);
    }

    static ImmutableTwoFallenPins gutter() {
        return gutter;
    }

    static ImmutableTwoFallenPins strike() {
        return strike;
    }

    boolean isGutter(int tryCount){
        return FallenPins.MIN_FALLEN_PIN_COUNT == getPins(tryCount).getPrimitive();
    }

    int totalScore(){
        return twoFallenPins.stream()
                            .mapToInt(FallenPins::getPrimitive)
                            .sum();
    }

    public int firstFallenPinsValue(){
        return getFallenPins(0);
    }

    public int secondFallenPinsValue(){
        return getFallenPins(1);
    }
}
