package bowling.domain;

import java.util.List;

import bowling.domain.frame.state.Score;

public class BowlingGameFrameRecord {
    private final List<Score> scores;

    public BowlingGameFrameRecord(List<Score> scores) {
        this.scores = scores;
    }

    public List<Score> getScores() {
        return scores;
    }
}
