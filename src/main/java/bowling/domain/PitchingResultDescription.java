package bowling.domain;

public class PitchingResultDescription {

    private String pitchingResultDescription;

    private PitchingResultDescription(String pitchingResultDescription) {
        this.pitchingResultDescription = pitchingResultDescription;
    }

    public static PitchingResultDescription of(String pitchingResultDescription) {
        return new PitchingResultDescription(pitchingResultDescription);
    }
}
