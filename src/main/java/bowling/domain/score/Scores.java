package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Scores {
    private static final int DEFAULT_PLAY_COUNT = 2;
    private static final int BONUS_PLAY_COUNT = 3;
    private static final int FIRST_PLAY = 0;
    private static final int SECOND_PLAY = 1;

    private final List<Score> scores;

    public Scores() {
        scores = new ArrayList<>();
    }

    public void add(Score score) {
        scores.add(score);
    }

    public int size() {
        return scores.size();
    }

    public int currentPinCount() {
        return scores.stream()
                .mapToInt(Score::getPin)
                .sum();
    }

    public String pinToScore(int index) {
        return scores.get(index).pinToScore();
    }

    public List<Score> getScores() {
        return Collections.unmodifiableList(scores);
    }

    public boolean isPlayable() {
        return scores.size() < DEFAULT_PLAY_COUNT;
    }

    public boolean isBonusPlayable() {
        return scores.size() < BONUS_PLAY_COUNT;
    }

    public boolean isSecondPlay() {
        return scores.size() == SECOND_PLAY;
    }

    public boolean isFirstPlay() {
        return scores.size() == FIRST_PLAY;
    }

    public boolean isStrike(int index) {
        return scores.get(index).isEqualKind(ScoreKind.STRIKE);
    }

    public boolean isSpare(int index) {
        return scores.get(index).isEqualKind(ScoreKind.SPARE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores that = (Scores) o;
        return Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}
