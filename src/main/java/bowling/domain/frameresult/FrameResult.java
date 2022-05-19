package bowling.domain.frameresult;

import bowling.domain.frame.Frame;

import java.util.Optional;

public interface FrameResult {

    Optional<Integer> score(Frame nextFrame);
}
