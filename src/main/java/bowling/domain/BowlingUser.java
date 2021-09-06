package bowling.domain;

import bowling.view.BowlingScore;

import java.util.ArrayList;
import java.util.List;

public class BowlingUser {
    private PlayerName playerName;
    private List<String> scores = new ArrayList<>();
    private static final BowlingScore bowlingScore = new BowlingScore();

    public BowlingUser(String name) {
        this.playerName = new PlayerName(name);
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
        return playerName.getName();
    }

    public void updateThirdResult(int thirdBowlingScore) {
        scores.set(scores.size() - 1, bowlingScore.thirdTransfer(scores.get(scores.size() - 1), thirdBowlingScore));
    }
}
