package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class ScoreResult {

    private final List<Integer> scores = new ArrayList<>();

    private Username username;

    private ScoreResult() {
    }

    public static ScoreResult ofUsername(Username username) {
        ScoreResult scoreResult = new ScoreResult();
        scoreResult.username = username;
        return scoreResult;
    }

    public void addScore(Integer score) {
        scores.add(score);
    }

    public List<Integer> getScores() {
        return new ArrayList<>(scores);
    }

    public boolean isSameUsername(Username username) {
        return this.username.equals(username);
    }
}
