package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    private final List<Score> scores;

    private Scores() {
        this.scores = new ArrayList<>();
    }

    public static Scores newInstance() {
        return new Scores();
    }

    public void sumScore(final Frame frame) {
        Score score = frame.getScore();

        if (!score.isCalculable()) {
            return;
        }

        if (scores.isEmpty()) {
            scores.add(score);
            return;
        }

        addScore(score);
    }

    private void addScore(final Score newScore) {
        Score currentScore = getCurrentScore();
        Score nextScore = currentScore.add(newScore);

        scores.add(nextScore);
    }

    private Score getCurrentScore() {
        return this.scores.get(scores.size() - 1);
    }

    public List<Score> getScores() {
        return this.scores;
    }
}
