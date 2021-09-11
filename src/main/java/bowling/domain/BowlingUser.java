package bowling.domain;

import java.util.List;

public class BowlingUser {
    private PlayerName playerName;
    private Scores scores = new Scores();

    public BowlingUser(String name) {
        this.playerName = new PlayerName(name);
    }

    public void addResult(Integer score) {
        scores.add(score);
    }

    public List<String> getScores() {
        return scores.getScores();
    }

    public void updateResult(int secondScore) {
        scores.update(secondScore);
    }

    public String getName() {
        return playerName.getName();
    }

    public void updateThirdResult(int thirdBowlingScore) {
        scores.updateThirdScore(thirdBowlingScore);
    }
}
