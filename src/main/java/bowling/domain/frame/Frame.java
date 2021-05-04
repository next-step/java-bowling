package bowling.domain.frame;

import bowling.domain.dto.FrameResponse;
import bowling.domain.state.PitchState;

public interface Frame {
    Frame pitch(int point);

    boolean isEnd();

    PitchState getPitchState();

    FrameResponse getFrameResponse();

    boolean equals(Object o);

    int hashCode();

    Round getRound();
}
