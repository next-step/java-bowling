package bowling.domain.frames;

import bowling.domain.Pitching;
import bowling.domain.pitchings.NormalFramePitchings;
import bowling.domain.pitchings.Pitchings;

import java.util.Optional;

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

    protected Optional<Integer> getScore() {
        if (!isEnd()) {
            return Optional.empty();
        }

        return calculateScore();
    }

    private Optional<Integer> calculateScore() {
        if (isStrike(pitchings)) {
            Optional<Pitching> nextPitching = getNextPitching();
            Optional<Pitching> nextAndNextPitching = getNextAndNextPitching();
            return pitchings.getTotalScoreWithStrikeBonus(nextPitching, nextAndNextPitching);
        }

        if (isSpare(pitchings)) {
            Optional<Pitching> nextPitching = getNextPitching();
            return pitchings.calculateTotalScoreWithSpareBonus(nextPitching);
        }

        int totalScore = pitchings.calculateTotalScore();
        return Optional.of(totalScore);
    }

    private boolean isSpare(Pitchings pitchings) {
        return pitchings.contains(Pitching.SPARE);
    }

    private boolean isStrike(Pitchings pitchings) {
        return pitchings.contains(Pitching.STRIKE);
    }

    private Optional<Pitching> getNextAndNextPitching() {
        return nextFrame.getSecondPitching();
    }

    private Optional<Pitching> getNextPitching() {
        return nextFrame.getFirstPitching();
    }

    Optional<Pitching> getFirstPitching() {
        return pitchings.getFirstPitching();
    }

    Optional<Pitching> getSecondPitching() {
        if (isStrike(pitchings)) {
            return nextFrame.getFirstPitching();
        }
        return pitchings.getSecondPitching();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
