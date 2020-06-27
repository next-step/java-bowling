package bowling.domain.frame;

import bowling.domain.pitch.NormalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;

import java.util.List;

public class NormalFrame implements Frame {
    private static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    private final Pitches pitches;
    private Frame nextFrame;

    private NormalFrame(Pitches pitches) {
        this.pitches = pitches;
    }

    public static Frame initiate() {
        return new NormalFrame(new NormalPitches());
    }

    public Frame next(int index) {
        this.nextFrame =
                (index != MAXIMUM_NORMAL_FRAME_INDEX) ? new NormalFrame(new NormalPitches()) : FinalFrame.ofFinal();
        return this.nextFrame;
    }

    @Override
    public void bowl(Score score) {
        pitches.throwBall(score);
    }

    @Override
    public boolean isMovableToNextFrame() {
        return pitches.isHavingSameCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS) || pitches.isStrike();
    }

    @Override
    public boolean isStrike() {
        return pitches.isStrike();
    }

    @Override
    public boolean isSpare() {
        return pitches.isSpare();
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
        FrameScore frameScore = createFrameScore();
        if (frameScore == null) {
            return frameScore;
        }
        if (frameScore.isAbleToCalculate()) {
            return frameScore;
        }
        return nextFrame.delegate(frameScore);
    }

    @Override
    public FrameScore delegate(FrameScore frameScore) {
        FrameScore nextFrameScore = frameScore.next(pitches.getCurrentScoreByIndex(0));
        if (nextFrameScore.isAbleToCalculate()) {
            return nextFrameScore;
        }
        if (nextFrame == null) {
            return null;
        }
        if (pitches.isStrike()) {
            return nextFrame.delegate(nextFrameScore);
        }
        if (pitches.isHavingSameCounts(2)) {
            return nextFrameScore.next(pitches.getCurrentScoreByIndex(1));
        }
        return null;
    }

    private FrameScore createFrameScore() {
        if (pitches.isStrike()) {
            return FrameScore.ofStrike();
        }
        if (pitches.isSpare()) {
            return FrameScore.ofSpare();
        }
        if (pitches.isHavingSameCounts(1)) {
            return null;
        }
        return FrameScore.ofMiss(pitches.getPitchScoreSum());
    }
}
