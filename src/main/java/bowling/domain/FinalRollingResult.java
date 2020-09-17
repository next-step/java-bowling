package bowling.domain;

public class FinalRollingResult extends RollingResult {

    private boolean extraFrame = false;

    @Override
    public void bowl(Pin pin) {
        rollingPin.add(pin);

        record(pin);
        isCanExtraFrame(pin);
    }

    @Override
    public boolean isFinish() {
        return rollingPin.size() > 1 && !extraFrame || rollingPin.size() > 2;
    }

    private void isCanExtraFrame(Pin pin) {
        if (pin.count() == PIN_COUNT_TOTAL || countOfFirstAndSecondPins() == PIN_COUNT_TOTAL) {
            extraFrame = true;
        }
    }
}
