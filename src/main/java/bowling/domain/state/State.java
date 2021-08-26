package bowling.domain.state;

import java.util.List;

public interface State {
    State nextPitch(int pins);
    State lastPitch(int pins);
    String display();
    boolean isFinished();
    List<State> lastSpare(List<State> list, State state);
}
