package bowling.domain;

public class NormalRollingResult extends RollingResult {

    private final String PIN_BOWL_RANGE = "한 프레임에 쓰러트릴수 있는 핀의 총합은 0 ~ 10 사이입니다.";

    @Override
    public void bowl(Pin pin) {
        rollingPin.add(pin);

        if (countOfFirstAndSecondPins() > PIN_COUNT_TOTAL) {
            throw new IllegalArgumentException(PIN_BOWL_RANGE);
        }

        record(pin);
    }

    @Override
    public boolean isFinish() {
        return rollingPin.size() > 1 || countOfFirstAndSecondPins() == PIN_COUNT_TOTAL;
    }

}
