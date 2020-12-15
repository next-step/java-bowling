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

    @Override
    public Pitching getFirstPitching() {
        Pitchings pitchings = super.getPitchings();
        return pitchings.getFirstPitching();
    }

    @Override
    public Pitching getSecondPitching() {
        Pitchings pitchings = super.getPitchings();
        if (isStrike(pitchings)) {
            return nextFrame.getFirstPitching();
        }
        return pitchings.getSecondPitching();
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
