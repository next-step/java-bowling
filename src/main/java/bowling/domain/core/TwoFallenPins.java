package bowling.domain.core;

import static bowling.domain.core.FallenPins.zero;
import static java.util.Arrays.asList;

final class TwoFallenPins extends AbstractTwoFallenPins {
    private int zeroBaseRollingIndex;

    TwoFallenPins() {
        super(asList(zero(), zero()));
        initTowFallenPins();
    }

    void initTowFallenPins() {
        zeroBaseRollingIndex = 0;
    }

    static TwoFallenPins mutable(){
        return new TwoFallenPins();
    }

    ImmutableTwoFallenPins immutable(){
        return new ImmutableTwoFallenPins(towFallenPins);
    }

    TwoFallenPins collect(FallenPins fallenPins){
        if (isComplete() || isStrike()){
            initTowFallenPins();
        }
        towFallenPins.set(zeroBaseRollingIndex++, fallenPins);
        return this;
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
            return new Strike();
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

        return new Gutter();
    }
}
