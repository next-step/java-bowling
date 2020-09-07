package camp.nextstep.edu.rebellion.bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class BonusScore {
    private final List<Score> scores;

    private BonusScore() {
        this.scores = new ArrayList<>();
    }

    public static BonusScore clear() {
        return new BonusScore();
    }

    public void mark(int hits) {
        this.scores.add(Score.of(hits));
    }

    public int getHitsScore() {
        return this.scores
                .stream()
                .mapToInt(Score::getHits)
                .sum();
    }
}
