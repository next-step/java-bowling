package bowling.domain.core.state;

import java.util.ArrayList;

import bowling.domain.core.FallenPins;

import static bowling.domain.core.FallenPins.empty;
import static java.util.Arrays.asList;

final class TwoFallenPins extends AbstractTwoFallenPins {
    private int zeroBaseRollingIndex;

    TwoFallenPins() {
        super(asList(empty(), empty()));
        initTwoFallenPins();
    }

    public void initTwoFallenPins() {
        firstFallenPins(empty());
        secondFallenPins(empty());
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
        verifySecondBowlFallenPins();
    }

    public TwoFallenPins collect(int firstFallenPins, int secondFallenPins){
        firstFallenPins(firstFallenPins);
        secondFallenPins(secondFallenPins);
        return this;
    }

    void verifySecondBowlFallenPins(){
        if (!empty().equals(secondFallenPins())) {
            firstFallenPins().verifySecondBowlFallenPins(secondFallenPins());
        }
    }

    boolean isComplete(){
        return MAX_TOW_FALLEN_PINS_SIZE == zeroBaseRollingIndex;
    }
}
