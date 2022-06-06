package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalFrame implements Frame {
    private final int index;
    private final List<Integer> scores = new ArrayList<>();

    private int remain;
    private int tryNo;

    public NormalFrame(int index) {
        this(index, 2);
    }

    public NormalFrame(int index, int tryNo) {
        this.index = index;
        this.tryNo = tryNo;
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

    public int score() {
        int score = new Random().nextInt(10 - remain) + 1;
        remain += score;
        tryNo--;
        scores.add(score);
        return score;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (tryNo < 1 || remain > 9) {
            int nextIndex = index + 1;
            tryNo = 2;
            remain = 0;
            return nextIndex;
        }
        return index;
    }

    @Override
    public boolean notLastFrame() {
        return this.index < 11;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "index=" + index +
                ", scores=" + scores +
                '}';
    }
}
