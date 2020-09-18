package bowling.domain.rolling;


import bowling.domain.Pin;

public class NormalRolling extends Rolling {

    private static final String PIN_BOWL_RANGE = "한 프레임에 쓰러트릴수 있는 핀의 총합은 0 ~ 10 사이입니다.";

    @Override
    public void bowl(Pin pin) {
        rollingPin.add(pin);
        if (countOfFirstAndSecondPins() > Pin.MAXIMUM_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_BOWL_RANGE);
        }
        if (rollingPin.size() == 1) {
            record(pin.record());
            return;
        }
        record(beforePin().record(pin));
    }

    @Override
    public boolean isFinish() {
        if (firstPin().isStrike() || rollingPin.size() > 1) {
            return true;
        }

        return false;
    }

}
