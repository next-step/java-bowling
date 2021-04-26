package bowling.domain.state;

import bowling.dto.statedto.StateDTO;

public interface State {
    int MAX_PINS = 10;
    boolean isFinished();
    String state();
    State bowl(int pitch);
    StateDTO exportStateDTO();
}
