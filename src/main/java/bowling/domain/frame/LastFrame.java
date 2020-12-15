package bowling.domain.frame;

import bowling.domain.Pitching;
import bowling.domain.pitchings.LastFramePitchings;
import bowling.domain.pitchings.Pitchings;

public class LastFrame extends Frame {
    private static final String NEXT_FRAME_INVOKE_ERR_MSG = "LastFrame은 NextFrame이 존재하지 않습니다.";
    private final int index;
    private Frame previousFrame;

    private LastFrame(int index, Frame previousFrame) {
        super(LastFramePitchings.getInstance());
        this.index = index;
        this.previousFrame = previousFrame;
    }

    public static Frame of(int index, Frame previousFrame) {
        return new LastFrame(index, previousFrame);
    }

    @Override
    public Frame initNextFrame() {
        throw new IllegalStateException(NEXT_FRAME_INVOKE_ERR_MSG);
    }

    @Override
    public Pitching getNextPitching() {
        Pitchings pitchings = super.getPitchings();
        return pitchings.getNextPitching();
    }

    @Override
    public Pitching getNextAndNextPitching() {
        Pitchings pitchings = super.getPitchings();
        return pitchings.getNextAndNextPitching();
    }

    @Override
    public Integer calculateScore() {
        if (!isEnd()) {
            return null;
        }

        Pitchings pitchings = super.getPitchings();
        return pitchings.getTotalScore();
    }

    @Override
    public Integer getTotalScore() {
        if (!isEnd() || calculateScore() == null) {
            return null;
        }

        if (previousFrame == null) {
            totalScore = calculateScore();
            return totalScore;
        }

        totalScore = previousFrame.getTotalScore() + calculateScore();
        return totalScore;
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + super.getPitchings() +
                '}';
    }
}
