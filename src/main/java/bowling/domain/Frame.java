package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private List<Integer> scores = new ArrayList<>();
    private boolean isEnd;


    public void addScore(int score) {
        this.scores.add(score);
        this.isEnd = isEndCondition(score);
    }

    private boolean isEndCondition(int score) {
        return this.scores.size() > 1 || score == 10;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
