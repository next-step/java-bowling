package bowling.domain.core;

import java.util.ArrayList;

import static bowling.domain.core.FallenPins.zero;
import static bowling.domain.core.Gutter.gutter;
import static bowling.domain.core.Strike.strike;
import static java.util.Arrays.asList;

final class TwoFallenPins extends AbstractTwoFallenPins {
    private int zeroBaseRollingIndex;

    TwoFallenPins() {
        super(asList(zero(), zero()));
        initTwoFallenPins();
    }

    void initTwoFallenPins() {
        firstFallenPins(zero());
        secondFallenPins(zero());
        zeroBaseRollingIndex = 0;
    }

    static TwoFallenPins mutable(){
        return new TwoFallenPins();
    }

    ImmutableTwoFallenPins immutable(){
        return new ImmutableTwoFallenPins(new ArrayList<>(twoFallenPins));
    }

    void collect(FallenPins fallenPins){
        if (isComplete() || isStrike()){
            initTwoFallenPins();
        }
        twoFallenPins.set(zeroBaseRollingIndex++, fallenPins);
    }

    TwoFallenPins collect(int firstFallenPins, int secondFallenPins){
        firstFallenPins(firstFallenPins);
        secondFallenPins(secondFallenPins);
        return this;
    }

    void verifySecondBowlFallenPins(){
        firstFallenPins().verifySecondBowlFallenPins(secondFallenPins());
    }

    boolean isComplete(){
        return MAX_TOW_FALLEN_PINS_SIZE == zeroBaseRollingIndex;
    }

    boolean isNotComplete(){
        return !isComplete();
    }

    public RolledResult toRolledResult() {
        if(isStrike()){
            return strike;
        }

        if (isNotComplete()){
            return new IncompleteState(firstFallenPins());
        }

        verifySecondBowlFallenPins();

        if (isSpare()) {
            return new Spare(immutable());
        }

        if (isMiss()) {
            return new Miss(immutable());
        }

        return gutter;
    }
}
