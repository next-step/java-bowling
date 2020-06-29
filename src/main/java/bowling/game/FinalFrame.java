package bowling.game;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FinalFrame implements Frame {

    private final FrameNumber frameNumber;
    private final Pitches pitches;

    public FinalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitches = new FinalPitches();
    }

    @Override
    public int bowl(final int pinCount) {
        return pitches.throwBall(pinCount);
    }

    @Override
    public boolean hasRemainChance() {
        return pitches.hasChance();
    }

    @Override
    public Frame createNextFrame() {
        throw new IllegalStateException("마지막 프레임은 다음 프레임을 만들 수 없습니다.");
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }

    @Override
    public String getStates() {
        return pitches.getPitchesStates();
    }

    @Override
    public Optional<Score> calculateScore() {
        if (!hasRemainChance()) {
            return Optional.of(new Score(pitches.getBasicScore(), 0));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Score> calculateBonusScore(Score beforeScore) {
        List<Integer> pinCounts = pitches.getPitchesPinCounts();

        for (int pinCount : pinCounts) {
            beforeScore = addBonusScore(beforeScore, pinCount);
        }

        return beforeScore.canCalculateScore() ? Optional.of(beforeScore) : Optional.empty();
    }

    private Score addBonusScore(Score beforeScore, final int pinCount) {
        if (beforeScore.canCalculateScore()) {
            return beforeScore;
        }

        return beforeScore.addBonusScore(pinCount);
    }
}
