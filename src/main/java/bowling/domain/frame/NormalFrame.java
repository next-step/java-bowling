package bowling.domain.frame;

import bowling.domain.Pitching;
import bowling.domain.pitchings.NormalFramePitchings;
import bowling.domain.pitchings.Pitchings;

public class NormalFrame extends Frame {
    private final int index;
    private Frame previousFrame;
    private Frame nextFrame;

    private NormalFrame(int index, Frame previousFrame) {
        super(NormalFramePitchings.getInstance());
        this.index = index;
        this.previousFrame = previousFrame;
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

    @Override
    public Integer calculateScore() {
        if (!isEnd()) {
            return null;
        }

        Pitchings pitchings = super.getPitchings();
        if (pitchings.contains(Pitching.STRIKE)) {
            Pitching nextPitching = nextFrame.getNextPitching();
            Pitching nextAndNextPitching = nextFrame.getNextAndNextPitching();
            if (nextPitching == null || nextAndNextPitching == null) {
                return null;
            }

            if (nextAndNextPitching == Pitching.SPARE) {
                return Pitching.STRIKE.getScore() + nextAndNextPitching.getScore();
            }

            return Pitching.STRIKE.getScore() + nextPitching.getScore() + nextAndNextPitching.getScore();
        }

        if (pitchings.contains(Pitching.SPARE)) {
            Pitching nextPitching = nextFrame.getNextPitching();
            if (nextPitching == null) {
                return null;
            }

            return Pitching.SPARE.getScore() + nextPitching.getScore();
        }

        return pitchings.getTotalScore();
    }

    @Override
    public Pitching getNextPitching() {
        Pitchings pitchings = super.getPitchings();
        return pitchings.getNextPitching();
    }

    @Override
    public Pitching getNextAndNextPitching() {
        Pitchings pitchings = super.getPitchings();
        if (pitchings.contains(Pitching.STRIKE)) {
            return nextFrame.getNextPitching();
        }
        return pitchings.getNextAndNextPitching();
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
        return "NormalFrame{" +
                "index=" + index +
                ", pitchings=" + super.getPitchings() +
                '}';
    }
}
