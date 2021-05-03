package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private final List<Score> scores;

    public Scores() {
        scores = new ArrayList<>();
    }

    public void accumulate(Score score) {
        if (score.isProgressState()) {
            return;
        }

        if (scores.isEmpty()) {
            scores.add(score);
            return;
        }

        Score nextScore = scores.get(scores.size() - 1).add(score);
        scores.add(nextScore);
    }

    public List<Score> toList() {
        return scores;
    }
}
