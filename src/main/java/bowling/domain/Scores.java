package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class Scores {

    private static final int CALCULATION_NOT_COMPLETED = -1;
    private static final int FIRST_FRAME = 0;

    private final Map<Integer, Score> scores;

    public Scores() {
        this.scores = new HashMap<>();;
    }

    public Map<Integer, Score> getScores() {
        return scores;
    }

    public int size() {
        return scores.size();
    }

    public void record(int frameNumber, int score) {
        if (score == CALCULATION_NOT_COMPLETED) {
            scores.put(frameNumber, new Score(CALCULATION_NOT_COMPLETED));
            return;
        }
        scores.put(frameNumber, new Score(cumulativeScore(frameNumber) + score));
    }

    public int cumulativeScore(int frameNumber) {
        if (frameNumber == FIRST_FRAME) {
            return 0;
        }
        return scores.get(frameNumber - 1).score();
    }
}
