package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private final List<Score> scores;

    public Scores() {
        this(new ArrayList<>());
    }

    public Scores(final List<Score> scores) {
        this.scores = scores;
    }

    public void accumulate(final Frame frame) {
        Score score = frame.getScore();
        if (score.isNotAddable()) {
            return;
        }

        if (scores.isEmpty()) {
            scores.add(score);
            return;
        }

        Score prev = scores.get(scores.size() - 1);
        Score nextScore = prev.add(score);
        scores.add(nextScore);
    }

    public List<Score> getScores() {
        return scores;
    }
}
