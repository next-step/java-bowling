package bowling.domain;

import bowling.util.BowlingScore;

import java.util.ArrayList;
import java.util.List;

public class BowlingUser {
    private String name;
    private int totalScore = 0;
    private List<String> scores = new ArrayList<>();
    private static final BowlingScore bowlingScore = new BowlingScore();

    public BowlingUser(String name) {
        this.name = name;
    }

    public void addResult(Integer score) {
        scores.add(bowlingScore.firstTransfer(score));
    }

    public List<String> getScores() {
        return scores;
    }

    public void updateResult(int secondScore) {
        Integer firstScore = Integer.parseInt(scores.get(scores.size() - 1));
        scores.set(scores.size() - 1, bowlingScore.secondTransfer(firstScore, secondScore));
    }

    public String getName() {
        return name;
    }
}
