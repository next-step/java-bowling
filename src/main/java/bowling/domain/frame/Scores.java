package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private final List<Integer> scores;

    public Scores() {
        scores = new ArrayList<>();
    }

    public void add(int score) {
        scores.add(score);
    }

    public int size() {
        return scores.size();
    }

    public int first() {
        return scores.get(0);
    }

    public int second() {
        return scores.get(1);
    }

    public int third() {
        return scores.get(2);
    }
}
