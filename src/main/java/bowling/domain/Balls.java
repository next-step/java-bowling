package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Balls {
    private List<Ball> balls = new ArrayList<>();
    public Balls() {
        this(0, 0);
    }

    public Balls(int firstBallKnockedDownPinCount, int secondBallKnockedDownPinCount) {
        Ball firstBall = new Ball(firstBallKnockedDownPinCount);
        Ball secondBall = new Ball(secondBallKnockedDownPinCount);
        this.balls.add(firstBall);
        this.balls.add(secondBall);
    }


    public void pitch(Pins pins) {
        for (Ball ball : balls) {
            ball.pitch(pins);
        }
    }

    public void add(Ball ball) {
        balls.add(ball);
    }

    public int size() {
        return balls.size();
    }

    public int getKnockedDownPinCount(int index) {
        return balls.get(index).getKnockedDownPinCount();
    }
}
