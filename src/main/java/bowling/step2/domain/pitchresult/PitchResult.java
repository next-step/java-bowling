package bowling.step2.domain.pitchresult;

import bowling.step2.domain.state.PitchStatus;

public class PitchResult {
    private final PitchStatus pitchStatus;

    private final Integer count;

    public PitchResult(PitchStatus pitchStatus, Integer count) {
        this.pitchStatus = pitchStatus;
        this.count = count;
    }

    public static PitchResult of(PitchStatus pitchStatus, Integer count) {
        return new PitchResult(pitchStatus, count);
    }

    public String result() {
        return pitchStatus.result(count);
    }
}
