package bowling.domain;

public class FinalFrame implements Frame {

    private final PitchesGroup pitchesGroup = new PitchesGroup();

    @Override
    public void bowl(int hitCounts) {
        pitchesGroup.recordPitch(hitCounts);
    }
}
