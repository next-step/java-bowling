package bowling_step3.domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    private List<Integer> scores = new ArrayList<>();

    public Scores() {
    }

    public static Scores of() {
        return new Scores();
    }

    public void add(int count) {
        scores.add(count);
    }

    public List<Integer> getScores() {
        return scores;
    }

    public int size() {
        return scores.size();
    }
}
