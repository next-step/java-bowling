package bowling.domain.frames;

import bowling.domain.Pitching;
import bowling.domain.pitchings.NormalFramePitchings;
import bowling.domain.pitchings.Pitchings;

import java.util.Optional;

public class NormalFrame extends FrameImpl {
    protected final Pitchings pitchings;
    private final AdjacentFrame adjacentFrame;

    protected NormalFrame(int index, FrameImpl previousFrame) {
        super(index);
        pitchings = NormalFramePitchings.getInstance();
        adjacentFrame = AdjacentFrame.of(previousFrame, initNextFrame());
    }

    public static FrameImpl getFirstFrame() {
        return new NormalFrame(1, null);
    }

    @Override
    public FrameImpl getLastFrame() {
        FrameImpl nextFrame = adjacentFrame.getNextFrame();
        return nextFrame.getLastFrame();
    }

    @Override
    public FrameImpl initNextFrame() {
        int nextFrameIndex = index + 1;
        int lastFrameIndex = FramesImpl.MAX_FRAME_SIZE;
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
        return adjacentFrame.getNextAndNextPitching();
    }

    private Optional<Pitching> getNextPitching() {
        return adjacentFrame.getNextPitching();
    }

    Optional<Pitching> getFirstPitching() {
        return pitchings.getFirstPitching();
    }

    Optional<Pitching> getSecondPitching() {
        if (isStrike(pitchings)) {
            return adjacentFrame.getNextPitching();
        }
        return pitchings.getSecondPitching();
    }

    @Override
    public FrameImpl getNextFrame() {
        return adjacentFrame.getNextFrame();
    }

    @Override
    protected Integer getPreviousTotalScore() {
        return adjacentFrame.getPreviousTotalScore();
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
