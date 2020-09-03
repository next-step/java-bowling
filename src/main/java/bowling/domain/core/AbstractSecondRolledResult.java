package bowling.domain.core;

import java.util.List;

import static java.util.Arrays.asList;

abstract class AbstractSecondRolledResult implements RolledResult {
    static final String ERROR_MESSAGE = "볼링 투구 횟수는 2회 입니다.(첫번째 투구(0), 두번째 투구(1))";
    private final List<Pins> towFallenPins;

    public AbstractSecondRolledResult(Pins first, Pins second) {
        towFallenPins = asList(first, second);
    }

    protected Pins getPins(int index){
        verifyRollingTrialCount(index);
        return towFallenPins.get(index);
    }

    @Override
    public int countOfFallenPinsByRolls(int rollingTryCount) {
        return getPins(rollingTryCount).getFallenPins();
    }

    private void verifyRollingTrialCount(int rollingTrialCount) {
        if (0 > rollingTrialCount || towFallenPins.size() <= rollingTrialCount){
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    String gutterOrFallenPinCount(int rollingTrialCount) {
        Pins pins = getPins(rollingTrialCount);
        if (pins.isGutter()){
            return "-";
        }
        return String.valueOf(pins.getFallenPins());
    }
}
