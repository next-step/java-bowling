package bowling.domain.frame;

import bowling.domain.pitch.FinalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int MAXIMUM_FINAL_FRAME_INDEX = 11;

    private final Pitches pitches;

    private FinalFrame(Pitches pitches) {
        this.pitches = pitches;
    }

    public static FinalFrame ofFinal() {
        return new FinalFrame(new FinalPitches());
    }

    @Override
    public Frame next(int index) {
        return null;
    }

    @Override
    public void bowl(Score score) {
        pitches.throwBall(score);
    }

    @Override
    public boolean isMovableToNextFrame() {
        if (pitches.isHavingSameCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS)
                && pitches.isNotContainingStrikeOrSpare()) {
            return true;
        }
        return pitches.isHavingSameCounts(Pitches.MAXIMUM_FINAL_PITCH_COUNTS);
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public List<String> getScoreSignatures() {
        return pitches.getScoreSignatures();
    }

    @Override
    public int getPitchScoreSum() {
        return pitches.getPitchScoreSum();
    }

    @Override
    public FrameScore getFrameScore() {
        return null;
    }

    @Override
    public FrameScore delegate(FrameScore frameScore) {
        return null;
    }
}
