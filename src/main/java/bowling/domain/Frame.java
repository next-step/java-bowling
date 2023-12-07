package bowling.domain;

public class Frame {
    private Ball firstBall;
    private Ball secondBall;

    public Frame(Ball firstBall, Ball secondBall) {
        this.firstBall = firstBall;
        this.secondBall = secondBall;
    }

    public static Frame fromFirstBall(Ball firstBall) {
        return new Frame(firstBall, Ball.emptySecondBall());
    }

    public static Frame of(Ball firstBall, Ball secondBall) {
        return new Frame(firstBall, secondBall);
    }

    public static Frame init() {
        return new Frame(Ball.init(), Ball.init());
    }

    public void applyFirstBall(Ball ball) {
        firstBall = ball;
    }

    public void applySecondBall(Ball ball) {
        secondBall = ball;
        secondBall.applySpareCase(firstBall);
    }

    public boolean isStrike() {
        return firstBall.isStrike();
    }

    public boolean isSpare() {
        return secondBall.isSpare();
    }

    public Ball getFirstBall() {
        return firstBall;
    }

    public Ball getSecondBall() {
        return secondBall;
    }

    public boolean isEmpty() {
        return firstBall.isEmpty() && secondBall.isEmpty();
    }
}
