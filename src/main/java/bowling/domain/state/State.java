package bowling.domain.state;

import bowling.dto.StateDTO;

public interface State {
    final int MAX_PINS = 10;
    boolean isFinished();
    String state();
    State bowl(int pins);
    StateDTO exportStateDTO();
}
