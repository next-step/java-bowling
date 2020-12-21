package bowling.dto;

public class FrameDto {
    private final PitchingsDto pitchings;
    private final Integer totalScore;

    private FrameDto(PitchingsDto pitchings, Integer totalScore) {
        this.pitchings = pitchings;
        this.totalScore = totalScore;
    }

    public static FrameDto of(PitchingsDto pitchings, Integer totalScore) {
        return new FrameDto(pitchings, totalScore);
    }

    public PitchingsDto getPitchings() {
        return pitchings;
    }

    public Integer getTotalScore() {
        return totalScore;
    }
}
