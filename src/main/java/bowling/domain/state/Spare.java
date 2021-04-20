package bowling.domain.state;

import static bowling.domain.Pin.BOWLING_PIN_MAX_SIZE;

public class Spare extends BaseState {

    private final int firstPin;
    private final int secondPin;

    public Spare(final int firstPin, final int secondPin) {
        if (firstPin + secondPin != BOWLING_PIN_MAX_SIZE) {
            throw new IllegalStateException("Spare 상태가 아닙니다.");
        }
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String printResult() {
        return isGutter(firstPin) + "|/";
    }

}
