package bowling.domain.frame;

import java.util.Random;

public class FinalFrame implements Frame {

    private static final Random RANDOM = new Random();
    private final int index;
    private final Scores scores;

    private int tryNo;
    private int totalScore;
    private int bonus = 1;

    public FinalFrame(int index) {
        this(index, 2, 0, new Scores());
    }

    public FinalFrame(int index, int tryNo, int totalScore, Scores scores) {
        this.index = index;
        this.tryNo = tryNo;
        this.totalScore = totalScore;
        this.scores = scores;
    }

    @Override
    public int attempt() {
        int score = getScore();
        totalScore += score;
        tryNo--;
        if (isBonus()) {
            tryNo += bonus--;
            totalScore = 0;
        }
        scores.add(score);

        return score;
    }

    private boolean isBonus() {
        return tryNo < 2 && totalScore == 10;
    }

    private int getScore() {
        return RANDOM.nextInt(10 - totalScore) + 1;
    }

    public Scores scores() {
        return scores;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (moveable()) { // 다 던지면 끝
            return index + 1;
        }
        return index;
    }

    private boolean moveable() {
        return tryNo < 1;
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
        return "FinalFrame{" +
                "index=" + index +
                ", scores=" + scores +
                '}';
    }
}
