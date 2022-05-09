package bowling.domain.frame;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Pins;
import bowling.domain.score.Score;
import java.util.Optional;

public interface Frame {

    void roll(int downPins);

    Optional<Frame> nextRound();

    FrameInfo frameInfo();

    Pins pins();

    Score numberOfDownedPins();

    boolean canRoll();

    boolean isFrameEnd(int currentFrame);

    Optional<Score> calcScore(Frames playerFrames);

    default int compareTo(Frame other) {
        return frameInfo().compareTo(other.frameInfo());
    }

    default boolean isRolled() {
        return !pins().isReady();
    }
}
