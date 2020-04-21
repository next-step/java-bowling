package bowling.domain.frame;

import bowling.domain.score.FinalScoreCalculator;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreCalculator;
import bowling.domain.pitch.Pitch;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int MAX_PITCH_SIZE = 3;

    private FramePitch framePitch;
    private ScoreCalculator scoreCalculator;

    public FinalFrame() {
        this.framePitch = new FramePitch();
        this.scoreCalculator = new FinalScoreCalculator();
    }

    private boolean hasThirdChance() {
        return framePitch.isSecondPitchSpare() ||
                framePitch.isFirstPitchStrike();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (isDone()) {
            return false;
        }

        if (framePitch.add(pinCount)) {
            scoreCalculator = scoreCalculator.add(framePitch.getLastPitch());
            return true;
        }
        return false;
    }

    @Override public Score getScore() {
        return scoreCalculator.calculateScore();
    }

    @Override public Score getScoreForOnePitch() {
        return framePitch.getFirstPitchScore();
    }

    @Override public Score getScoreForTwoPitches() {
        return framePitch.getFirstPitchScore()
                .add(framePitch.getSecondPitchPinCount());
    }

    @Override public boolean isDone() {
        return framePitch.size() == MAX_PITCH_SIZE ||
                (!hasThirdChance() && framePitch.size() == 2);
    }

    @Override public List<Pitch> getFramePitch() {
        return framePitch.getPitches();
    }

    @Override public Frame createNext() {
        return null;
    }

    @Override public Frame createNext(Frame frame) {
        return null;
    }

    @Override public boolean isLast() {
        return true;
    }
}
