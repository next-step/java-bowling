package bowling.domain.core.state;

import java.util.ArrayList;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

import static bowling.domain.core.FallenPins.zero;
import static bowling.domain.core.state.Gutter.gutter;
import static bowling.domain.core.state.Strike.strike;
import static java.util.Arrays.asList;

final class TwoFallenPins extends AbstractTwoFallenPins {
    private int zeroBaseRollingIndex;

    TwoFallenPins() {
        super(asList(zero(), zero()));
        initTwoFallenPins();
    }

    public void initTwoFallenPins() {
        firstFallenPins(zero());
        secondFallenPins(zero());
        zeroBaseRollingIndex = 0;
    }

    public static TwoFallenPins mutable(){
        return new TwoFallenPins();
    }

    ImmutableTwoFallenPins immutable(){
        return new ImmutableTwoFallenPins(new ArrayList<>(twoFallenPins));
    }

    public void collect(FallenPins fallenPins){
        if (isComplete() || isStrike()){
            initTwoFallenPins();
        }
        twoFallenPins.set(zeroBaseRollingIndex++, fallenPins);
    }

    public TwoFallenPins collect(int firstFallenPins, int secondFallenPins){
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
