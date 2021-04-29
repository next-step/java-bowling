package bowling.domain.state;

import bowling.domain.FrameScore;
import bowling.dto.StateDTO;

public interface State {
    boolean isFinished();
    String state();
    State stateAfterPitch(int pitch);
    FrameScore frameScore();
    FrameScore frameScoreWithBonus(FrameScore prevFrameScore);
    StateDTO exportStateDTO();
}
