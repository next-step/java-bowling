package bowling.model;

import java.util.ArrayList;
import java.util.List;

public class FrameScore {

    private final List<Score> scores;

    public FrameScore() {
        this.scores = new ArrayList<>();
    }

    public void add(final int scoreCount) {
        scores.add(Score.of(scoreCount));
    }

    public int sum() {
        return Score.sum(scores);
    }

    public boolean isSameScoreCount(final int count) {
        return scores.size() == count;
    }

    public boolean canBowlOneMore() {
        if (scores.size() == 1) {
            return FrameScoreResult.canBowlOneMoreByFirstStrike(scores);
        }

        return FrameScoreResult.canBowlOneMore(scores);
    }

    public List<Score> getScores() {
        return scores;
    }
}
