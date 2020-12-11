package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.pitchings.LastFramePitchings;
import bowling.domain.pitchings.Pitchings;

public class LastFrame implements Frame {
    private static final String NEXT_FRAME_INVOKE_ERR_MSG = "LastFrame은 NextFrame이 존재하지 않습니다.";
    private final int index;
    private final Pitchings pitchings;

    private LastFrame(int index) {
        this.index = index;
        pitchings = LastFramePitchings.getInstance();
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
        pitchings.addPitching(knockDownPins);
        return this;
    }

    @Override
    public Pitchings getPitchings() {
        return pitchings;
    }

    @Override
    public boolean isEnd() {
        return pitchings.isEnd();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
