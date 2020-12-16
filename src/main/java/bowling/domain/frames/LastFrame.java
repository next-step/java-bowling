package bowling.domain.frames;

import bowling.domain.Pitching;
import bowling.domain.pitchings.LastFramePitchings;
import bowling.domain.pitchings.Pitchings;

public class LastFrame extends Frame {
    private static final String NEXT_FRAME_INVOKE_ERR_MSG = "LastFrame은 NextFrame이 존재하지 않습니다.";
    private final int index;

    private LastFrame(int index, Frame previousFrame) {
        super(LastFramePitchings.getInstance(), previousFrame);
        this.index = index;
    }

    public static Frame of(int index, Frame previousFrame) {
        return new LastFrame(index, previousFrame);
    }

    @Override
    public Frame initNextFrame() {
        throw new IllegalStateException(NEXT_FRAME_INVOKE_ERR_MSG);
    }

    @Override
    public Pitching getFirstPitching() {
        Pitchings pitchings = super.getPitchings();
        return pitchings.getFirstPitching();
    }

    @Override
    public Pitching getSecondPitching() {
        Pitchings pitchings = super.getPitchings();
        return pitchings.getSecondPitching();
    }

    protected Integer calculateScore() {
        if (!isEnd()) {
            return null;
        }

        Pitchings pitchings = super.getPitchings();
        return pitchings.calculateTotalScore();
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + super.getPitchings() +
                '}';
    }
}
