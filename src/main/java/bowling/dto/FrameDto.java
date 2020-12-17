package bowling.dto;

import bowling.domain.pitchings.Pitchings;

import java.util.Optional;

public class FrameDto {
    private final Pitchings pitchings;
    private final Optional<Integer> totalScore;

    private FrameDto(Pitchings pitchings, Optional<Integer> totalScore) {
        this.pitchings = pitchings;
        this.totalScore = totalScore;
    }

    public static FrameDto of(Pitchings pitchings, Optional<Integer> totalScore) {
        return new FrameDto(pitchings, totalScore);
    }

    public Pitchings getPitchings() {
        return pitchings;
    }

    public Optional<Integer> getTotalScore() {
        return totalScore;
    }
}
