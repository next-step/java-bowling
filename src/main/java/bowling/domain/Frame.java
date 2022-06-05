package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final int index;
    private final List<Integer> scores = new ArrayList<>();

    public Frame(int index) {
        this.index = index;
    }

    public boolean equal(int index) {
        return this.index == index;
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public List<Integer> scores() {
        return scores;
    }
}
