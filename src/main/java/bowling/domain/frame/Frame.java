package bowling.domain.frame;

import bowling.domain.FrameScore;
import bowling.dto.FrameDTO;

public interface Frame {
    int FIRST_FRAME_NO = 1;
    int LAST_FRAME_NO = 10;
    void bowl(int pitch);
    boolean isFinished();
    Frame next();
    FrameScore frameScore();
    FrameScore frameScoreWithBonus(FrameScore prevFrameScore);
    FrameDTO exportFrameDTO();
}
