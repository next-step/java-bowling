package bowling.domain.frame;

import bowling.domain.pitchings.LastFramePitchings;

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
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + super.getPitchings() +
                '}';
    }
}
