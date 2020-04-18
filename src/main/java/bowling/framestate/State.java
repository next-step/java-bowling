package bowling.framestate;

import bowling.FrameScore;

import java.util.List;

public interface State {

    State bowl(int countOfPin);

    FrameScore createFrameScore();

    FrameScore addingUpFrameScore(FrameScore beforeScore);

    boolean isOver();

    List<Integer> getPins();
}
