package bowling.refactor.framestate;

import bowling.refactor.FrameScore;

public interface State {

    State Bowl(int countOfPin);

    FrameScore createFrameScore();

    FrameScore addingUpFrameScore(FrameScore beforeScore);
}
