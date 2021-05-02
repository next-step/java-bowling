package bowling.domain.state;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.dto.StateDTO;

public interface State {
    boolean isFinished();
    String state();
    State stateAfterPitch(Pins pitch);
    FrameScore frameScore();
    FrameScore frameScoreWithBonus(FrameScore prevFrameScore);
    StateDTO exportStateDTO();
}
