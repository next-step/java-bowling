package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinalFrame implements Frame {

    private final int index;
    private final List<Integer> scores = new ArrayList<>();

    private int tryNo;
    private int leftScore;
    private int bonus = 1;

    public FinalFrame(int index) {
        this(index, 2, 0);
    }

    public FinalFrame(int index, int tryNo, int leftScore) {
        this.index = index;
        this.tryNo = tryNo;
        this.leftScore = leftScore;
    }

    @Override
    public int score() {
        int score = new Random().nextInt(10 - leftScore) + 1;
        leftScore += score;
        tryNo--;
        if (tryNo < 2 && leftScore == 10) {
            tryNo += bonus--;
            leftScore = 0;
        }
        scores.add(score);

        return score;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (tryNo < 1) { // 다 던지면 끝
            return index + 1;
        }
        return index;
    }

    @Override
    public boolean equal(int index) {
        return this.index == index;
    }

    @Override
    public List<Integer> scores() {
        return scores;
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "index=" + index +
                ", scores=" + scores +
                '}';
    }
}
