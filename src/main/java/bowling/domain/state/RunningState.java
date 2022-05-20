package bowling.domain.state;

import bowling.domain.Frame;

public interface RunningState extends ThrowingState {
    Frame frame();
}
