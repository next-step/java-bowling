package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;
import bowling.domain.state.FrameState;

import java.util.List;

public interface Frame {
    Frame bowl(FallenPin fallenPin);

    boolean isFinished();

    List<FrameState> getStates();

    boolean isReady();

    Score getScore();

    Score addScore(Score previousScore);
}
