package bowling.domain.frame;

import bowling.domain.pitch.NormalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.Score;

import java.util.List;

public class NormalFrame implements Frame {
    private static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    private final int index;
    private final NormalPitches normalPitches = new NormalPitches();
    private Frame nextFrame;
    private Pitches pitches;

    private NormalFrame(int index, Pitches pitches) {
        this.index = index;
        this.pitches = pitches;
    }

    public static NormalFrame initiate() {
        return new NormalFrame(FIRST_INDEX, new NormalPitches());
    }

    public Frame next2() {
        this.nextFrame = new NormalFrame(index + NEXT_INDEX, new NormalPitches());
        return this.nextFrame;
    }

    public Frame last() {
        this.nextFrame = FinalFrame.last(index + NEXT_INDEX);
        return this.nextFrame;
    }

    @Override
    public void bowl(Score score) {
        normalPitches.throwBall(score);
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
}
