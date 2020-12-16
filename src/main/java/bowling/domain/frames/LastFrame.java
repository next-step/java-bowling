package bowling.domain.frames;

import bowling.domain.Pitching;
import bowling.domain.pitchings.LastFramePitchings;

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
        return pitchings.getFirstPitching();
    }

    @Override
    public Pitching getSecondPitching() {
        return pitchings.getSecondPitching();
    }

    protected Integer calculateScore() {
        if (!isEnd()) {
            return null;
        }

        return pitchings.calculateTotalScore();
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
