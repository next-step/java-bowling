package bowling.dto;

import bowling.domain.pitchings.Pitchings;

public class FrameDto {
    private final Pitchings pitchings;
    private final Integer totalScore;

    private FrameDto(Pitchings pitchings, Integer totalScore) {
        this.pitchings = pitchings;
        this.totalScore = totalScore;
    }

    public static FrameDto of(Pitchings pitchings, Integer totalScore) {
        return new FrameDto(pitchings, totalScore);
    }

    public Pitchings getPitchings() {
        return pitchings;
    }

    public Integer getTotalScore() {
        return totalScore;
    }
}
