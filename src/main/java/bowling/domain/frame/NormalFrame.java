package bowling.domain.frame;

import bowling.domain.pitch.NormalPitches;
import bowling.domain.score.FrameNumericScore;
import bowling.domain.score.Score;

import java.util.List;

public class NormalFrame implements Frame {
    private static final int FIRST_INDEX = 1;
    private static final int NEXT_INDEX = 1;
    private static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    private final NormalPitches normalPitches = new NormalPitches();
    private final int index;

    private NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame initiate() {
        return new NormalFrame(FIRST_INDEX);
    }

    @Override
    public Frame next() {
        return index == MAXIMUM_NORMAL_FRAME_INDEX ?
                FinalFrame.last(index + NEXT_INDEX) : new NormalFrame(index + NEXT_INDEX);
    }

    @Override
    public void bowl(Score score) {
        normalPitches.throwBall(score);
    }

    @Override
    public boolean isMovableToNextFrame() {
        return normalPitches.isFinished(MAXIMUM_NORMAL_PITCH_COUNTS) || normalPitches.isStrike();
    }

    @Override
    public List<String> getScoreSignatures() {
        return normalPitches.getScoreSignatures();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public FrameNumericScore calculateFrameScore(Frame lastFrame) {
        if (lastFrame.isSpare() || lastFrame.isStrike()) {
            return calculateFrameScoreWhenStrikeOrSpare(lastFrame);
        }
        return FrameNumericScore.of(lastFrame.getScoresSum());
    }

    private FrameNumericScore calculateFrameScoreWhenStrikeOrSpare(Frame lastFrame) {
        int frameScore = lastFrame.getScoresSum() + normalPitches.getScoresSum();
        if (lastFrame.isStrike() && normalPitches.isFinished(MAXIMUM_NORMAL_PITCH_COUNTS)) {
            return FrameNumericScore.of(frameScore);
        }
        return lastFrame.isSpare() && normalPitches.isFinished(FIRST_INDEX) ? FrameNumericScore.of(frameScore) : null;
    }

    @Override
    public boolean isStrike() {
        return normalPitches.isStrike();
    }

    @Override
    public boolean isSpare() {
        return normalPitches.isSpare();
    }

    @Override
    public int getScoresSum() {
        return normalPitches.getScoresSum();
    }
}
