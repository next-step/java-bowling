package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class Scores {

    private static final int CALCULATION_NOT_COMPLETED = -1;

    private final Map<FrameStrategy, Score> scores;

    public Scores() {
        this.scores = new HashMap<>();
    }

    public int score(FrameStrategy frame) {
        if (scores.get(frame) == null) {
            return CALCULATION_NOT_COMPLETED;
        }
        return scores.get(frame).score();
    }

    public int size() {
        return scores.size();
    }

    public void record(FrameStrategy frame, FrameStrategy previousFrame) {
        if (frame.score() == CALCULATION_NOT_COMPLETED) {
            scores.put(frame, new Score(CALCULATION_NOT_COMPLETED));
            return;
        }
        scores.put(frame, new Score(cumulativeScore(previousFrame) + frame.score()));
    }

    public int cumulativeScore(FrameStrategy previousFrame) {
        if (previousFrame == null) {
            return 0;
        }
        return score(previousFrame);
    }
}
