package bowling.domain.frames;

import bowling.domain.pitchings.Pitchings;

import java.util.Optional;

public interface FrameViewDto {
    Pitchings getPitchings();

    Optional<Integer> getTotalScore();
}
