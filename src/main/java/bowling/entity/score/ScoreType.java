package bowling.entity.score;

import bowling.entity.Pin;

public interface ScoreType {

    String scoreResult();

    boolean isFrameEnd();

    ScoreType bowl(Pin fallenPin);
}
