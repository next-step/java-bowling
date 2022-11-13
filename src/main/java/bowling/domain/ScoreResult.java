package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class ScoreResult {

    private final List<Integer> scores = new ArrayList<>();

    private Username username;

    public void addScore(Integer score) {
        scores.add(score);
    }

    public void addUsername(Username username) {
        this.username = username;
    }

    public List<Integer> getScores() {
        return new ArrayList<>(scores);
    }

    public boolean isSameUsername(Username username) {
        return this.username.equals(username);
    }
}
