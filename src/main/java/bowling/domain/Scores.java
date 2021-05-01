package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
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
            scores.add(Score.ofNone(lastScore.calculateScore() + score.calculateScore()));
        }
        scores.add(score);
    }

    private boolean isEmpty() {
        return scores.isEmpty();
    }

    public List<Score> scores() {
        return Collections.unmodifiableList(scores);
    }

    public Score roundScore(int roundCount) {
        return scores.get(roundCount - 1);
    }
}
