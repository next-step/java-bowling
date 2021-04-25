package bowling.domain.engine.frame;

import bowling.domain.engine.PitchResult;
import bowling.dto.RecordsDto;

public interface Frame {

    void throwBall(PitchResult pitchResult);
    boolean isEnded();
    RecordsDto export();

}
