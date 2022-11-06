package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;

import java.util.List;

public interface FrameState {

    FrameState bowl(FallenPin fallenPin);

    boolean isFinished();

    List<FallenPin> getFallenPins();

    int tries();

    Score getScore();

    Score addScore(Score previousScore);
}
