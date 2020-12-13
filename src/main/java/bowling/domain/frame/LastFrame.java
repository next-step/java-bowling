package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.pitchings.LastFramePitchings;
import bowling.domain.pitchings.Pitchings;

public class LastFrame extends Frame {
    private static final String NEXT_FRAME_INVOKE_ERR_MSG = "LastFrame은 NextFrame이 존재하지 않습니다.";
    private final int index;

    private LastFrame(int index) {
        super(LastFramePitchings.getInstance());
        this.index = index;
    }

    public static Frame getInstance(int index) {
        return new LastFrame(index);
    }

    @Override
    public Frame initNextFrame() {
        throw new IllegalStateException(NEXT_FRAME_INVOKE_ERR_MSG);
    }

    @Override
    public Frame setKnockDownPins(KnockDownPins knockDownPins) {
        Pitchings pitchings = super.getPitchings();
        pitchings.addPitching(knockDownPins);
        return this;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + super.getPitchings() +
                '}';
    }
}
