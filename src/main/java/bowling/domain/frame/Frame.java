package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.score.CommonScore;
import bowling.domain.score.Score;

import java.util.List;

public interface Frame extends FrameResult {

    boolean isBowlingFinish();

    default void hitPins(Pins pins) {
    }

    default void addFrame(List<Frame> frames) {
    }

    Score getScore();

    Score addBonusScore(Score score);
}
