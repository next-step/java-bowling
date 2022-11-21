package bowling.domain;

import java.util.Optional;

public interface FrameStrategy {

    boolean isFinal();
    boolean isFrameEnd(int round, Result beforeResult);

    Optional<Score> getScore(RollingResult rollingResult);
}
