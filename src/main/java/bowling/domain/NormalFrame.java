package bowling.domain;

public class NormalFrame {

    private final PitchesGroup pitchesGroup;

    public NormalFrame(PitchesGroup pitchesGroup) {
        this.pitchesGroup = pitchesGroup;
    }

    public void bowl(int hitCounts) {
        pitchesGroup.recordPitch(hitCounts);
    }
}
