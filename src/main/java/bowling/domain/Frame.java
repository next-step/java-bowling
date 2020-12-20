package bowling.domain;

import java.util.List;

import static bowling.util.Lists.getAsOptional;

public interface Frame {
    Frame throwBall(int fallingPins);

    Scoring getScoring();

    int getNumber();

    List<BallThrow> getBallThrows();

    Frame getNextFrame();

    Score getScore();

    default FrameStatus getFrameStatus() {
        return new FrameStatus(getFallingPins(0),
                               getFallingPins(1),
                               getFallingPins(2));
    }

    default boolean isFinish() {
        return false;
    }

    default Integer getFallingPins(int index) {
        return getAsOptional(getBallThrows(), index)
                .map(BallThrow::getFallingPins)
                .orElse(null);
    }
}
