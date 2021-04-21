package bowling.domain.state;

import static bowling.domain.Pin.BOWLING_PIN_MAX_SIZE;

public class Spare extends BaseState {

    private final int firstPin;

    public Spare(final int firstPin, final int secondPin) {
        if (firstPin + secondPin != BOWLING_PIN_MAX_SIZE) {
            throw new IllegalStateException("Spare 상태가 아닙니다.");
        }
        this.firstPin = firstPin;
    }

    @Override
    public String printResult() {
        return printScore(firstPin) + "|/";
    }

}
