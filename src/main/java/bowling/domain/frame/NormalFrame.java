package bowling.domain.frame;

import bowling.domain.pitch.NormalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameNumericScore;
import bowling.domain.score.Score;

import java.util.List;

public class NormalFrame implements Frame {
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
    public FrameNumericScore calculateFrameScore(Frame lastFrame) {
        if (lastFrame.isStrike() || lastFrame.isSpare()) {
            return calculateFrameScoreWhenStrikeOrSpare(lastFrame);
        }
        return FrameNumericScore.of(lastFrame.getPitchScoreSum());
    }

    private FrameNumericScore calculateFrameScoreWhenStrikeOrSpare(Frame lastFrame) {
        int frameScore = lastFrame.getPitchScoreSum() + normalPitches.getPitchScoreSum();
        if (lastFrame.isStrike() && normalPitches.isHavingSameCounts(PITCH_COUNT_FOR_CALCULATING_STRIKE)) {
            return FrameNumericScore.of(frameScore);
        }
        return lastFrame.isSpare() && normalPitches.isHavingSameCounts(PITCH_COUNT_FOR_CALCULATING_SPARE) ?
                FrameNumericScore.of(frameScore) : null;
    }

    @Override
    public boolean isMovableToNextFrame() {
        return normalPitches.isHavingSameCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS) || normalPitches.isStrike();
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
    public List<String> getScoreSignatures() {
        return normalPitches.getScoreSignatures();
    }

    @Override
    public int getPitchScoreSum() {
        return normalPitches.getPitchScoreSum();
    }

    @Override
    public int getIndex() {
        return index;
    }
}
