package bowling.domain.frames;

import bowling.domain.pitchings.Pitchings;

public interface FrameViewDto {
    Pitchings getPitchings();

    Integer getTotalScore();
}
