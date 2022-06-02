package bowling.domain;

import bowling.engine.ScoreStrategy;

public class NormalFrame implements bowling.engine.NormalFrame {

    private final FirstTry firstTry;
    private final SecondTry secondTry;

    private NormalFrame(ScoreStrategy scoreStrategy) {
        int first = scoreStrategy.createFirst();
        this.firstTry = new FirstTry(first);
        this.secondTry = new SecondTry(scoreStrategy.createSecond(first));
    }

    private NormalFrame(int num1, int num2) {
        this.firstTry = new FirstTry(num1);
        this.secondTry = new SecondTry(num2);
    }

    public static NormalFrame firstWithRandom(ScoreStrategy scoreStrategy) {
        return new NormalFrame(scoreStrategy);
    }

    public NormalFrame nextWithRandom(ScoreStrategy scoreStrategy) {
        return new NormalFrame(scoreStrategy);
    }

    public NormalFrame lastWithRandom(ScoreStrategy scoreStrategy) {
        return new NormalFrame(scoreStrategy);
    }

    public static NormalFrame firstWithFactor(int num1, int num2) {
        return new NormalFrame(num1, num2);
    }

    public NormalFrame nextWithFactor(int num1, int num2) {
        return new NormalFrame(num1, num2);
    }

    public NormalFrame lastWithFactor(int num1, int num2) {
        return new NormalFrame(num1, num2);
    }

    public FirstTry getFirstTry() {
        return firstTry;
    }

    public SecondTry getSecondTry() {
        return secondTry;
    }
}
