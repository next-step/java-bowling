package bowling.entity.frame;

import bowling.entity.Pin;
import bowling.entity.score.ScoreType;

public interface FrameInfo {
    ScoreType pinResult(Pin fallenPin);

    String scoreResult();
}
