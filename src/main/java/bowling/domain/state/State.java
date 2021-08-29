package bowling.domain.state;

import java.util.List;

public interface State {
    State nextPitch(int pins);
    State lastPitch(int pins);
    boolean isFinished();
    List<State> lastSpare(List<State> list, State state);
    int firstPins();
    int secondPins();
}
