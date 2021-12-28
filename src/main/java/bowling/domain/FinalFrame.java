package bowling.domain;

import static bowling.domain.Ball.MAX_PIN_COUNT;

public class FinalFrame implements Frame {
    private static final int MIN_BOWL_COUNT = 2;
    private static final int MAX_BOWL_COUNT = 3;

    Balls balls;
    private int count;

    private FinalFrame() {
        this.balls = FinalBalls.init();
        this.count = 0;
    }

    public static FinalFrame init() {
        return new FinalFrame();
    }

    @Override
    public Frame bowl(Ball ball) {
        count++;
        balls.bowl(ball);
        return this;
    }

    @Override
    public boolean isEnd() {
        if (count == MAX_BOWL_COUNT) {
            return true;
        }
        return count == MIN_BOWL_COUNT && total() < MAX_PIN_COUNT;
    }

    private int total() {
        return balls.total();
    }

    @Override
    public int getFrameIndex() {
        return FrameIndex.MAX;
    }

    @Override
    public String symbol() {
        return balls.symbol();
    }

}
