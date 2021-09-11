package bowling.domain;

import bowling.view.BowlingScore;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private static final BowlingScore bowlingScore = new BowlingScore();

    private List<String> scores = new ArrayList<>();

    public void add(Integer score) {
        scores.add(bowlingScore.firstTransfer(score));
    }


    public void update(int secondScore) {
        Integer firstScore = Integer.parseInt(scores.get(scores.size() - 1));
        scores.set(scores.size() - 1, bowlingScore.secondTransfer(firstScore, secondScore));
    }

    public List<String> getScores() {
        return scores;
    }

    public void updateThirdScore(int thirdBowlingScore) {
        scores.set(scores.size() - 1, bowlingScore.thirdTransfer(scores.get(scores.size() - 1), thirdBowlingScore));
    }
}
