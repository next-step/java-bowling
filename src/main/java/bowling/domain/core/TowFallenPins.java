package bowling.domain.core;

import static bowling.domain.core.Pins.zero;
import static java.util.Arrays.asList;

final class TowFallenPins extends AbstractTowFallenPins {
    private int zeroBaseRollingIndex;

    TowFallenPins() {
        super(asList(zero(), zero()));
        initTowFallenPins();
    }

    void initTowFallenPins() {
        zeroBaseRollingIndex = 0;
    }

    static TowFallenPins mutable(){
        return new TowFallenPins();
    }

    ImmutableTowFallenPins immutable(){
        return new ImmutableTowFallenPins(towFallenPins);
    }

    TowFallenPins collect(Pins fallenPins){
        if (isComplete() || isStrike()){
            initTowFallenPins();
        }
        towFallenPins.set(zeroBaseRollingIndex++, fallenPins);
        return this;
    }

    TowFallenPins collect(int firstFallenPins, int secondFallenPins){
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
