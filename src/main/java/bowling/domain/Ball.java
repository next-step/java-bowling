package bowling.domain;

public class Ball {
    private final int ball;
    private BallStatus ballStatus;

    public Ball(int ball, BallStatus ballStatus) {
        this.ball = ball;
        this.ballStatus = ballStatus;
    }

    public static Ball from(int ball) {
        BallStatus ballStatus = BallStatus.from(ball);
        return new Ball(ball, ballStatus);
    }

    public static Ball emptySecondBall() {
        return new Ball(0, BallStatus.NOTHING);
    }

    public static Ball of(int ball, BallStatus ballStatus) {
        return new Ball(ball, ballStatus);
    }

    public static Ball init() {
        return new Ball(0, BallStatus.NOTHING);
    }

    public int getBall() {
        return ball;
    }

    public BallStatus getBallStatus() {
        return ballStatus;
    }

    public boolean isStrike() {
        return ballStatus.isStrike();
    }

    public void applySpareCase(Ball firstBall) {
        if (firstBall.isTenWith(ball)) {
            ballStatus = BallStatus.SPARE;
        }
    }

    private boolean isTenWith(int ball) {
        return this.ball + ball == 10;
    }

    public boolean isSpare() {
        return ballStatus == BallStatus.SPARE;
    }

    public boolean isOpen() {
        return ballStatus == BallStatus.OPEN;
    }

    public boolean isEmpty() {
        return ballStatus == BallStatus.NOTHING;
    }
}
