package bowling.domain.core;

import java.util.List;

abstract class AbstractTowFallenPins {
    protected static final int MAX_TOW_FALLEN_PINS_SIZE = 2;
    static final String ERROR_MESSAGE = "볼링 투구 횟수는 2회 입니다.(첫번째 투구(0), 두번째 투구(1))";
    protected final List<Pins> towFallenPins;

    public AbstractTowFallenPins(List<Pins> towFallenPins) {
        this.towFallenPins = towFallenPins;
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
        return Pins.MAX_FALLEN_PIN_COUNT == firstFallenPins().plus(secondFallenPins());
    }

    boolean isStrike() {
        return Pins.MAX_FALLEN_PIN_COUNT == getFallenPins(0);
    }

    boolean isGutter(){
        return Pins.MIN_FALLEN_PIN_COUNT == firstFallenPins().plus(secondFallenPins());
    }

    Pins secondFallenPins() {
        return getPins(1);
    }

    AbstractTowFallenPins secondFallenPins(int pins) {
        return secondFallenPins(Pins.of(pins));
    }

    AbstractTowFallenPins secondFallenPins(Pins pins){
        towFallenPins.set(1, pins);
        return this;
    }

    Pins firstFallenPins() {
        return getPins(0);
    }

    AbstractTowFallenPins firstFallenPins(int pins){
        return firstFallenPins(Pins.of(pins));
    }

    AbstractTowFallenPins firstFallenPins(Pins pins){
        towFallenPins.set(0, pins);
        return this;
    }

    Pins getPins(int index){
        verifyRollingTrialCount(index);
        return towFallenPins.get(index);
    }

    int getFallenPins(int index){
        return getPins(index).getFallenPins();
    }
}
