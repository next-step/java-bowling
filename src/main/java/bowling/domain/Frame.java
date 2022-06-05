package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final int index;
    private final List<Integer> scores = new ArrayList<>();

    public Frame(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public List<Integer> scores() {
        return scores;
    }
}
