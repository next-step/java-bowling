package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FrameScore {

    private final List<Score> scores;

    public FrameScore() {
        this.scores = new ArrayList<>();
    }

    private FrameScore(final List<Score> scores) {
        this.scores = Collections.unmodifiableList(scores);
    }

    public static FrameScore newInstance(final List<Integer> scoreNumbers) {
        List<Score> scores = scoreNumbers.stream()
                .map(Score::of)
                .collect(Collectors.toList());

        return new FrameScore(scores);
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

    public FrameScoreResult getResult() {
        return FrameScoreResult.of(scores);
    }

    public List<Score> getScores() {
        return scores;
    }
}
