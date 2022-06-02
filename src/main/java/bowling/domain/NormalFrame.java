package bowling.domain;

import bowling.engine.ScoreStrategy;

public class NormalFrame implements bowling.engine.NormalFrame {

    private final FirstTry firstTry;
    private final SecondTry secondTry;

    public NormalFrame(ScoreStrategy scoreStrategy) {
        int first = scoreStrategy.createFirst();
        this.firstTry = new FirstTry(first);
        this.secondTry = new SecondTry(scoreStrategy.createSecond(first));
    }

    public NormalFrame(int num1, int num2) {
        this.firstTry = new FirstTry(num1);
        this.secondTry = new SecondTry(num2);
    }

    public FirstTry getFirstTry() {
        return firstTry;
    }

    public SecondTry getSecondTry() {
        return secondTry;
    }
}
