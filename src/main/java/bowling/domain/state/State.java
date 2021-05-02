package bowling.domain.state;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.dto.StateDTO;

public interface State {
    State stateAfterPitch(Pins pitch);
    FrameScore frameScore();
    FrameScore frameScoreWithBonus(FrameScore prevFrameScore);
    boolean isFinished();
    String state();
    StateDTO exportStateDTO();
}
