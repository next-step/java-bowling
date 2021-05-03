package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    private final List<Score> scores;

    private Scores() {
        this.scores = new ArrayList<>();
    }

    public static Scores init() {
        return new Scores();
    }

    public int size() {
        return scores.size();
    }

    public void addScore(Score score) {
        if (!isEmpty()) {
            Score lastScore = roundScore(size());
            scores.add(Score.of(lastScore.calculateScore() + score.calculateScore(), ScoreState.ofNone()));
            return;
        }
        scores.add(score);
    }

    private boolean isEmpty() {
        return scores.isEmpty();
    }

    public Score roundScore(int roundCount) {
        if (size() < roundCount) {
            return Score.of(-1, ScoreState.ofNone());
        }
        return scores.get(roundCount - 1);
    }
}
