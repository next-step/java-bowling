package bowling.domain.frameresult;

import java.util.Optional;

public interface FrameResult {

    Optional<Integer> calculateScore(Bonus bonus);
}
