package bowling.domain.score;

import java.util.List;

public class Scores {
    private final List<Integer> scores;

    public Scores(List<Integer> scores) {
        this.scores = scores;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public boolean checkSize(int pitchCount) {
        return pitchCount == scores.size();
    }

    public int getScoreSum() {
        int first = scores.get(0);
        int second = scores.get(1);
        return first + second;
    }

    public int getFistScore() {
        return scores.get(0);
    }

    public int getSecondScore() {
        return scores.get(1);
    }
}
