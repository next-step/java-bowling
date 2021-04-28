package bowling.domain.state;

import bowling.dto.StateDTO;

public interface State {
    boolean isFinished();
    String state();
    State stateAfterPitch(int pitch);
    StateDTO exportStateDTO();
}
