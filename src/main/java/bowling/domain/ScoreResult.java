package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class ScoreResult {

    private final List<Integer> scores = new ArrayList<>();

    public void addScore(Integer score) {
        scores.add(score);
    }

    public List<Integer> getScores() {
        return new ArrayList<>(scores);
    }
}
