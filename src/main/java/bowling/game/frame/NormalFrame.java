package bowling.game.frame;

import bowling.game.Score;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private final Pitches pitches;
    private Frame next;

    public NormalFrame() {
        this.pitches = new NormalPitches();
    }

    @Override
    public void bowl(final int pinCount) {
        pitches.throwBall(pinCount);
    }

    @Override
    public boolean hasRemainChance() {
        return pitches.hasChance();
    }

    @Override
    public Frame createNextFrame(int frameNumber) {
        if (frameNumber == FINAL_FRAME_NUMBER) {
            next = new FinalFrame();
            return next;
        }

        next = new NormalFrame();
        return next;
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    @Override
    public String getStates() {
        return pitches.getPitchesStates();
    }

    @Override
    public Optional<Score> calculateScore() {
        if (hasRemainChance()) {
            return Optional.empty();
        }

        Score score = createScore();

        if (score.canCalculateScore()) {
            return Optional.of(score);
        }

        return Objects.nonNull(next) ? next.calculateBonusScore(score) : Optional.empty();
    }

    @Override
    public Optional<Score> calculateBonusScore(Score beforeScore) {
        List<Integer> pinCounts = pitches.getPitchesPinCounts();

        for (int pinCount : pinCounts) {
            beforeScore = addBonusScore(beforeScore, pinCount);
        }

        if (beforeScore.canCalculateScore()) {
            return Optional.of(beforeScore);
        }

        return Objects.nonNull(next) && !hasRemainChance() ? next.calculateBonusScore(beforeScore)
                : Optional.empty();
    }

    private Score createScore() {
        if (pitches.isStrikePitches()) {
            return Score.ofStrike();
        }

        return pitches.isSparePitches() ? Score.ofSpare() : Score.ofMiss(pitches.getBasicScore());
    }

    private Score addBonusScore(Score beforeScore, final int pinCount) {
        if (beforeScore.canCalculateScore()) {
            return beforeScore;
        }

        return beforeScore.addBonusScore(pinCount);
    }
}
