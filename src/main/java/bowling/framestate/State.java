package bowling.framestate;

import bowling.FrameScore;

public interface State {

    State bowl(int countOfPin);

    FrameScore createFrameScore();

    FrameScore addingUpFrameScore(FrameScore beforeScore);

    boolean isOver();
}
