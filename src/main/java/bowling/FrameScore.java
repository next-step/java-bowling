package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.Pins.MAX_PIN_COUNT;

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

    public boolean isCount(final int count) {
        return scores.size() == count;
    }

    public boolean isStrike() {
        if (scores.size() >= 1) {
            Score firstScore = scores.get(0);
            return firstScore.isEqualsTo(MAX_PIN_COUNT);
        }

        return false;
    }

    public boolean isSpare() {
        return isCount(2) && sum() == MAX_PIN_COUNT;
    }

    public List<Score> getScores() {
        return scores;
    }
}
