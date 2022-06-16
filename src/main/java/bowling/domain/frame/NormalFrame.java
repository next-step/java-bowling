package bowling.domain.frame;

import java.util.Random;

public class NormalFrame implements Frame {
    private final int index;
    private final Scores scores;

    private int totalScore;
    private int tryNo;

    public NormalFrame(int index) {
        this(index, 2, new Scores());
    }

    public NormalFrame(int index, int tryNo, Scores scores) {
        this.index = index;
        this.tryNo = tryNo;
        this.scores = scores;
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public Scores scores() {
        return scores;
    }

    public int attempt() {
        int score = new Random().nextInt(10 - totalScore) + 1;
        totalScore += score;
        tryNo--;
        scores.add(score);
        return score;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (moveable()) {
            return index + 1;
        }
        return index;
    }

    private boolean moveable() {
        return tryNo < 1 || totalScore > 9;
    }

    @Override
    public boolean equal(int index) {
        return this.index == index;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "index=" + index +
                ", scores=" + scores +
                '}';
    }
}
