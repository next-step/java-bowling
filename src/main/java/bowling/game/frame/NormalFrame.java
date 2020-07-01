package bowling.game.frame;

import bowling.game.Score;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static bowling.game.frame.FinalFrame.FINAL_FRAME_NUMBER;

public class NormalFrame extends Frame {
    private Frame next;

    public NormalFrame() {
        this.pitches = new NormalPitches();
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
}
