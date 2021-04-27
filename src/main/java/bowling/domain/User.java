package bowling.domain;

import java.util.Map;

public class User {

    private final String name;
    private final Scores scores;

    public User(String name) {
        this.name = name;
        this.scores = new Scores();
    }

    public String name() {
        return name;
    }

    public Map<Integer, Score> getScores() {
        return scores.getScores();
    }

    public void recordScore(int frameNumber, int score) {
        scores.record(frameNumber, score);
    }

    public int scoreSize() {
        return scores.size();
    }
}
