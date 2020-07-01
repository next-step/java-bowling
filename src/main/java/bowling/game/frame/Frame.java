package bowling.game.frame;

import bowling.game.Score;

import java.util.Optional;

public abstract class Frame {
    protected Pitches pitches;

    abstract Frame createNextFrame(int frameNumber);

    abstract boolean isLastFrame();

    abstract Optional<Score> calculateScore();

    abstract Optional<Score> calculateBonusScore(Score beforeScore);

    protected void bowl(final int pinCount) {
        pitches.throwBall(pinCount);
    }

    protected boolean hasRemainChance() {
        return pitches.hasChance();
    }

    protected String getStates() {
        return pitches.getPitchesStates();
    }

    protected Score addBonusScore(Score beforeScore, final int pinCount) {
        if (beforeScore.canCalculateScore()) {
            return beforeScore;
        }

        return beforeScore.addBonusScore(pinCount);
    }
}
