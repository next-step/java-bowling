package bowling.framestate;

import bowling.FrameScore;
import bowling.Pin;

import java.util.List;

public interface State {

    State bowl(Pin pinCount);

    FrameScore createFrameScore();

    FrameScore addingUpFrameScore(FrameScore beforeScore);

    boolean isOver();

    List<Pin> getPins();
}
