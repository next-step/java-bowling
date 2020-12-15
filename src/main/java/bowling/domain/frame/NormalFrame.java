package bowling.domain.frame;

import bowling.domain.Pitching;
import bowling.domain.pitchings.NormalFramePitchings;

import java.util.List;

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
    public Integer getScore() {
        List<Pitching> value = super.getPitchings().getValue();
        if (value.contains(Pitching.STRIKE)) {
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

        if (value.contains(Pitching.SPARE)) {
            Pitching nextPitching = nextFrame.getNextPitching();
            if (nextPitching == null) {
                return null;
            }

            return Pitching.SPARE.getScore() + nextPitching.getScore();
        }

        return value.stream()
                .mapToInt(Pitching::getScore)
                .sum();
    }

    @Override
    public Pitching getNextPitching() {
        List<Pitching> value = getPitchings().getValue();
        if (value.isEmpty()) {
            return null;
        }
        return value.get(0);
    }

    @Override
    public Pitching getNextAndNextPitching() {
        List<Pitching> value = getPitchings().getValue();
        if (!value.isEmpty() && value.get(0) == Pitching.STRIKE) {
            return nextFrame.getNextPitching();
        }

        if (value.size() < 2) {
            return null;
        }

        return value.get(1);
    }

    @Override
    public Integer getTotalScore() {
        if (!isEnd() || getScore() == null) {
            return null;
        }

        if (previousFrame == null) {
            totalScore = getScore();
            return totalScore;
        }

        totalScore = previousFrame.getTotalScore() + getScore();
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
