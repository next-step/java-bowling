package bowling.domain.frame;

import java.util.Objects;
import java.util.Optional;

public class Score {
    private int score;
    private boolean needAdditionalScore = false;
    private Score additionalScore;

    public Score(int score) {
        this.score = score;
    }

    public Score(Pitches pitches) {
        if (pitches.isLastPitchStrike() || pitches.isLastPitchSpare()) {
            needAdditionalScore = true;
        }

        score = pitches.getPinCountTotal();
    }

    public Optional<Integer> getScore() {
        if (!isAbleToGetScore()) {
            return Optional.empty();
        }

        if (needAdditionalScore) {
            return Optional.of(score + additionalScore.score);
        }

        return Optional.of(score);
    }

    public boolean isAbleToGetScore() {
        if (!needAdditionalScore) {
            return true;
        }
        return !Objects.isNull(additionalScore);
    }

    public void setAdditionalScore(Score additionalScore) {
        this.additionalScore = additionalScore;
    }

    public Score add(Score addableScore) {
        return new Score(this.score + addableScore.score);
    }
}
