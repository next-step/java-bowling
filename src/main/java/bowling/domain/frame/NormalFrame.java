package bowling.domain.frame;

import bowling.domain.Pitching;
import bowling.domain.pitchings.NormalFramePitchings;
import bowling.domain.pitchings.Pitchings;

public class NormalFrame extends Frame {
    private final int index;
    private Frame nextFrame;

    protected NormalFrame(int index, Frame previousFrame) {
        super(NormalFramePitchings.getInstance(), previousFrame);
        this.index = index;
    }

    public static Frame getFirstFrame() {
        return new NormalFrame(1, null);
    }

    public static Frame getFrame(int index) {
        return new NormalFrame(index, null);
    }

    @Override
    public Frame initNextFrame() {
        int nextFrameIndex = index + 1;
        int lastFrameIndex = Frames.MAX_FRAME_SIZE;
        if (nextFrameIndex == lastFrameIndex) {
            nextFrame = LastFrame.of(lastFrameIndex, this);
            return nextFrame;
        }

        nextFrame = new NormalFrame(nextFrameIndex, this);
        return nextFrame;
    }

    protected Integer calculateScore() {
        if (!isEnd()) {
            return null;
        }

        Pitchings pitchings = super.getPitchings();
        if (isStrike(pitchings)) {
            Pitching nextPitching = getNextPitching();
            Pitching nextAndNextPitching = getNextAndNextPitching();
            return pitchings.calculateTotalScoreWithStrikeBonus(nextPitching, nextAndNextPitching);
        }

        if (isSpare(pitchings)) {
            Pitching nextPitching = getNextPitching();
            return pitchings.calculateTotalScoreWithSpareBonus(nextPitching);
        }

        return pitchings.calculateTotalScore();
    }

    private boolean isSpare(Pitchings pitchings) {
        return pitchings.contains(Pitching.SPARE);
    }

    private boolean isStrike(Pitchings pitchings) {
        return pitchings.contains(Pitching.STRIKE);
    }

    private Pitching getNextAndNextPitching() {
        return nextFrame.getSecondPitching();
    }

    private Pitching getNextPitching() {
        return nextFrame.getFirstPitching();
    }

    Pitching getFirstPitching() {
        Pitchings pitchings = super.getPitchings();
        return pitchings.getFirstPitching();
    }

    Pitching getSecondPitching() {
        Pitchings pitchings = super.getPitchings();
        if (isStrike(pitchings)) {
            return nextFrame.getFirstPitching();
        }
        return pitchings.getSecondPitching();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pitchings=" + super.getPitchings() +
                '}';
    }
}
