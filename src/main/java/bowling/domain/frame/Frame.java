package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;
import bowling.exception.CannotCalculateException;

public interface Frame {

    Frame bowl(Pins pins);

    boolean isFinish();

    boolean isReady();

    String getState();

    Score getScore();

    Score calculateAdditionalScore(Score score);

    default Score getScoreResult() {
        try {
            return getScore();
        } catch (CannotCalculateException e) {
            return Score.of();
        }
    }
}
