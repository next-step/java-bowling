package bowling.domian.state;

import bowling.domian.frame.Score;
import bowling.domian.frame.exception.InvalidScoreCalculateException;

public class Ready implements State {
    private static final String GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE = "아직 프레임이 끝나지 않아 계산이 불가능합니다!";

    @Override
    public State bowl(int falledPinsCount) {
        Pins pins = Pins.bowl(falledPinsCount);

        if (pins.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(pins);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean canGetScore() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new InvalidScoreCalculateException(GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE);
    }

    @Override
    public Score calculateAdditional(Score lastScore) {
        throw new InvalidScoreCalculateException(GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE);
    }
}
