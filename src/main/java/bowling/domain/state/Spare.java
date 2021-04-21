package bowling.domain.state;

import static bowling.domain.Pin.BOWLING_PIN_MAX_SIZE;

public class Spare extends BaseState {

    public static final String GUIDE_ERR_STATE_WRONG_SPARE_STATE = "Spare 상태가 아닙니다.";
    private final int firstPin;

    public Spare(final int firstPin, final int secondPin) {
        if (firstPin + secondPin != BOWLING_PIN_MAX_SIZE) {
            throw new IllegalArgumentException(GUIDE_ERR_STATE_WRONG_SPARE_STATE);
        }
        this.firstPin = firstPin;
    }

    @Override
    public String printResult() {
        return printScore(firstPin) + BOWLING_STATE_SPLIT_DELIMITER + BOWLING_STATE_SPARE;
    }

}
