package bowling.domain.engine;

import bowling.dto.RecordsDto;

public interface Frame {

    void throwBall(PitchResult pitchResult);
    boolean isEnded();
    RecordsDto export();

}
