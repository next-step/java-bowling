package bowling.domian.state;

import bowling.domian.frame.Score;
import bowling.domian.frame.exception.InvalidScoreCalculateException;

public class Ready implements State {
    private static final String GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE = "아직 투구를 하지 않아 계산이 불가능합니다!";

    public State bowl(int falledPinsCount) {
        Pins pins = Pins.bowl(falledPinsCount);

        if (pins.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(pins);
    }

    public boolean isFinished() {
        return false;
    }

    public boolean canGetScore() {
        return false;
    }

    public Score getScore() {
        throw new InvalidScoreCalculateException(GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE);
    }
}
