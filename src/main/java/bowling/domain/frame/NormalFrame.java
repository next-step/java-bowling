package bowling.domain.frame;

import bowling.domain.pin.Pins;

import static bowling.domain.Constants.BOWLING_FINAL_TRY;
import static bowling.domain.Constants.BOWLING_FIRST_TRY;
import static bowling.domain.pin.Pin.BOWLING_PIN_MAX_SIZE;

public class NormalFrame extends BaseFrame {

    private int tryCount;

    public NormalFrame() {
        this(BOWLING_FIRST_TRY);
    }

    public NormalFrame(final int round) {
        super(round);
        tryCount = BOWLING_FIRST_TRY;
    }

    public void bowl(final int downPins) {
        pins = bowling(downPins);
        state = state.bowl(downPins);
        tryCount = tryCount + 1;
    }

    private Pins bowling(final int downPin) {
        if (tryCount == BOWLING_FIRST_TRY) {
            return pins.first(downPin);
        }
        return pins.second(downPin);
    }

    @Override
    public int score() {
        return pins.normalScore();
    }

    @Override
    public boolean isNextFrame() {
        return this.score() == BOWLING_PIN_MAX_SIZE
                || tryCount == BOWLING_FINAL_TRY;
    }

    @Override
    public String printFrame() {
        if (tryCount == BOWLING_FIRST_TRY) {
            return state.printResult();
        }
        return state.printLastResult();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
