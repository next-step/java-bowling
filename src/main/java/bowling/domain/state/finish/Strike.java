package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.exception.state.StrikeStatePinException;

public class Strike extends Finish {

    private final String STRIKE_DESC = "X";

    public Strike(Pin first) {
        checkPinStrike(first);
    }

    public static void checkPinStrike(Pin pin) {
        if (!pin.isStrike()) {
            throw new StrikeStatePinException();
        }
    }

    @Override
    public Score createScore() {
        return Score.strike();
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return score.addPin(Pin.of(10));
    }

    @Override
    public String desc() {
        return STRIKE_DESC;
    }

}
