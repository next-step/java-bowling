package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;

public class Bonus {
    private final List<Score> scores;

    private Attempt attempt;

    private Bonus() {
        this.scores = new ArrayList<>();
        this.attempt = new Attempt(0);
    }

    public static Bonus clear() {
        return new Bonus();
    }

    public void markScore(int hits) {
        if (attempt.hasAttempt()) {
            this.scores.add(Score.of(hits));
            this.attempt.tried();
        }
    }

    public void giveChance(int attempts) {
        this.attempt = Attempt.reset(attempts);
    }

    public boolean hasChance() {
        return attempt.hasAttempt();
    }

    public int getHitsScore() {
        return this.scores
                .stream()
                .mapToInt(Score::getHits)
                .sum();
    }
}
