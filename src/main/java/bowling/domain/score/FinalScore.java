package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Optional;

public class FinalScore implements Score {
    private static final int DEFAULT_MAX_ADD_NUMBER = 2;
    private static final int INIT_VALUE = 0;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    private int maxAddNumber = DEFAULT_MAX_ADD_NUMBER;
    private int score;

    public FinalScore() {
        this.score = INIT_VALUE;
    }

    @Override public Optional<Integer> calculateScore(Frame frame) {
        return calculateScore();
    }

    @Override public Optional<Integer> calculateScore() {
        if (isAddingDone()) {
            return Optional.of(score);
        }

        return Optional.empty();
    }

    @Override public Score add(Pitch pitch) {
        if (isAddingDone()) {
            return this;
        }

        if(hasThirdChance(pitch)) {
            maxAddNumber += ONE;
        }

        maxAddNumber--;
        score += pitch.getPinCount();
        return this;
    }

    private boolean hasThirdChance(Pitch pitch) {
        return pitch.isSpare() || pitch.isStrike();
    }

    private boolean isAddingDone() {
        return maxAddNumber == ZERO;
    }
}
