package bowling.domain.engine;

import bowling.dto.RecordsDto;
import bowling.dto.ViewObject;

public interface Frame extends ViewObject<RecordsDto> {

    void throwBall(PitchResult pitchResult);
    boolean isEnded();

}
