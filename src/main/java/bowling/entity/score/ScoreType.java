package bowling.entity.score;

import bowling.entity.Pin;

public interface ScoreType {
    Pin score();

    String scoreResult();

    boolean isFrameEnd();

    ScoreType pinResult(Pin fallenPin);
}
