package bowling.domain.frames;

import bowling.domain.Pitching;
import bowling.domain.pitchings.NormalFramePitchings;
import bowling.domain.pitchings.Pitchings;

import java.util.Optional;

public class NormalFrame extends Frame {
    protected final Pitchings pitchings;
    private final AdjacentFrame adjacentFrame;

    protected NormalFrame(int index, Frame previousFrame) {
        super(index);
        pitchings = NormalFramePitchings.getInstance();
        adjacentFrame = AdjacentFrame.of(previousFrame, initNextFrame());
    }

    public static Frame getFirstFrame() {
        return new NormalFrame(1, null);
    }

    @Override
    public Frame getLastFrame() {
        Frame nextFrame = adjacentFrame.getNextFrame();
        return nextFrame.getLastFrame();
    }

    @Override
    public Frame initNextFrame() {
        int nextFrameIndex = index + 1;
        int lastFrameIndex = Frames.MAX_FRAME_SIZE;
        if (nextFrameIndex == lastFrameIndex) {
            return LastFrame.of(lastFrameIndex, this);
        }

        return new NormalFrame(nextFrameIndex, this);
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
        return adjacentFrame.getNextFrame().getSecondPitching();
    }

    private Optional<Pitching> getNextPitching() {
        return adjacentFrame.getNextFrame().getFirstPitching();
    }

    Optional<Pitching> getFirstPitching() {
        return pitchings.getFirstPitching();
    }

    Optional<Pitching> getSecondPitching() {
        if (isStrike(pitchings)) {
            return adjacentFrame.getNextFrame().getFirstPitching();
        }
        return pitchings.getSecondPitching();
    }

    @Override
    public Frame getNextFrame() {
        return adjacentFrame.getNextFrame();
    }

    @Override
    protected Frame getPreviousFrame() {
        return adjacentFrame.getPreviousFrame();
    }

    @Override
    public Pitchings getPitchings() {
        return pitchings;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
