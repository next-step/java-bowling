package bowling.domain.frames;

import bowling.domain.Pitching;
import bowling.domain.pitchings.LastFramePitchings;

import java.util.Optional;

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
    public Optional<Pitching> getFirstPitching() {
        return pitchings.getFirstPitching();
    }

    @Override
    public Optional<Pitching> getSecondPitching() {
        return pitchings.getSecondPitching();
    }

    protected Optional<Integer> getScore() {
        if (!isEnd()) {
            return Optional.empty();
        }

        int score = pitchings.calculateTotalScore();
        return Optional.of(score);
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
