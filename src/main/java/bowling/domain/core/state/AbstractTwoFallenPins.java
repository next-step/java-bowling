package bowling.domain.core.state;

import java.util.List;

import bowling.domain.core.FallenPins;

abstract class AbstractTwoFallenPins {
    protected static final int MAX_TOW_FALLEN_PINS_SIZE = 2;
    static final String ERROR_MESSAGE = "볼링 투구 횟수는 2회 입니다.(첫번째 투구(0), 두번째 투구(1))";
    protected final List<FallenPins> twoFallenPins;

    protected AbstractTwoFallenPins(List<FallenPins> twoFallenPins) {
        this.twoFallenPins = twoFallenPins;
    }

    private void verifyRollingTrialCount(int rollingTrialCount) {
        if (0 > rollingTrialCount || MAX_TOW_FALLEN_PINS_SIZE <= rollingTrialCount){
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    boolean isMiss() {
        return !isSpare() && !isGutter();
    }

    boolean isSpare() {
        return FallenPins.MAX_FALLEN_PIN_COUNT == firstFallenPins().plus(secondFallenPins());
    }

    boolean isStrike() {
        return FallenPins.MAX_FALLEN_PIN_COUNT == getFallenPins(0);
    }

    public boolean isGutter(){
        return FallenPins.MIN_FALLEN_PIN_COUNT == firstFallenPins().plus(secondFallenPins());
    }

    FallenPins secondFallenPins() {
        return getPins(1);
    }

    AbstractTwoFallenPins secondFallenPins(int pins) {
        return secondFallenPins(FallenPins.of(pins));
    }

    AbstractTwoFallenPins secondFallenPins(FallenPins fallenPins){
        twoFallenPins.set(1, fallenPins);
        return this;
    }

    FallenPins firstFallenPins() {
        return getPins(0);
    }

    AbstractTwoFallenPins firstFallenPins(int pins){
        return firstFallenPins(FallenPins.of(pins));
    }

    AbstractTwoFallenPins firstFallenPins(FallenPins fallenPins){
        twoFallenPins.set(0, fallenPins);
        return this;
    }

    FallenPins getPins(int index){
        verifyRollingTrialCount(index);
        return twoFallenPins.get(index);
    }

    public int getFallenPins(int index){
        return getPins(index).getPrimitive();
    }
}
