package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreResult {

    private final List<Integer> scores;
    private int totalScore;

    private ScoreResult() {
        this.totalScore = 0;
        this.scores = new ArrayList<>();
    }

    public static ScoreResult of() {
        return new ScoreResult();
    }

    public void addScoreResult(Score score) {
        if (score.canCalculateScore()) {
            totalScore += score.getScore();
            scores.add(totalScore);
        }
    }

    public List<Integer> getScores() {
        return Collections.unmodifiableList(scores);
    }

    public void clear() {
        totalScore = 0;
        scores.clear();
    }
}
