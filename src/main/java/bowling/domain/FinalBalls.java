package bowling.domain;

public class FinalBalls extends Balls {

    private FinalBalls() {
        super();
    }

    public static FinalBalls init() {
        return new FinalBalls();
    }

    @Override
    public Balls bowl(Ball ball) {
        balls.add(getLast().bowl(ball));
        if (getLast().isEnd()) {
            balls.add(Ball.first());
        }
        return this;
    }
}
