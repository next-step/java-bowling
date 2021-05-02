package bowling.entity.score;

import bowling.entity.Pin;
import bowling.entity.Score;

public interface ScoreType {

    String scoreResult();

    boolean isFrameEnd();

    ScoreType bowl(Pin fallenPin);

    Score score();

    Score calculate(Score beforeScore);
}
