package bowling.framestate;

import bowling.FrameScore;

public interface State {

    State Bowl(int countOfPin);

    FrameScore createFrameScore();

    FrameScore addingUpFrameScore(FrameScore beforeScore);
}
