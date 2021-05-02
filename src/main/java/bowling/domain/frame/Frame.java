package bowling.domain.frame;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.dto.FrameDTO;

public interface Frame {
    int FIRST_FRAME_NO = 1;
    int LAST_FRAME_NO = 10;
    void bowl(Pins pitch);
    FrameScore frameScore();
    FrameScore frameScoreWithBonus(FrameScore prevFrameScore);
    boolean isFinished();
    Frame next();
    FrameDTO exportFrameDTO();
}
