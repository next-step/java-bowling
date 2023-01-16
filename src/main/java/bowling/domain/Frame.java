package bowling.domain;

import java.util.Optional;

public interface Frame {

    void bowl(Pin pin);

    boolean isFinished();

    Frame nextFrame();

    int frameNumber();

    Optional<Integer> calculateScore();

    Optional<Integer> calculateLastFrameScore(Score lastScore);

}
