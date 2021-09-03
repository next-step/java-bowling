package bowling.step2.outputView.pitchResult;

import bowling.step2.outputView.state.PitchStatus;

public class PitchResult {
    private PitchStatus pitchStatus;

    private Integer count;

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
