package bowling.step3.domain;

public class NormalFrame extends Frame {
    private static final int DEFAULT_PITCH_CHANCE = 2;

    public Boolean isEndedFrame() {
        return this.pitches.getSize() == DEFAULT_PITCH_CHANCE || this.pitches.hasStrike();
    }
}
