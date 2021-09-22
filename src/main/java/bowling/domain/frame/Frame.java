package bowling.domain.frame;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Status;
import bowling.domain.score.Score;
import java.util.Optional;

public interface Frame {

    void roll(int downPins);

    Optional<Frame> nextRound();

    FrameInfo frameInfo();

    Status pinStatus();

    Score numberOfDownedPins();

    boolean hasNextRound();

    boolean isCurrentFrameEnd(int currentFrame);

    Optional<Score> calcScore(Frames playerFrames);

    default int compareTo(Frame other) {
        return frameInfo().compareTo(other.frameInfo());
    }

    default boolean isRolled() {
        return !pinStatus().equals(Status.READY);
    }
}
