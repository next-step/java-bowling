package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Balls {
    private List<Ball> balls = new ArrayList<>();

    private Pins pins;
    public Balls() {
        this(0, 0);
    }

    public Balls(int firstBallKnockedDownPinCount, int secondBallKnockedDownPinCount) {
        Ball firstBall = new Ball(firstBallKnockedDownPinCount);
        Ball secondBall = new Ball(secondBallKnockedDownPinCount);
        this.balls.add(firstBall);
        this.balls.add(secondBall);
        this.pins = new Pins();
        pins.knockDown(firstBallKnockedDownPinCount);
        pins.knockDown(secondBallKnockedDownPinCount);
    }

    public Balls(int firstBallKnockedDownPinCount, int secondBallKnockedDownPinCount, int thirdBallKnockedDownPinCount) {
        Ball firstBall = new Ball(firstBallKnockedDownPinCount);
        Ball secondBall = new Ball(secondBallKnockedDownPinCount);
        this.balls.add(firstBall);
        this.balls.add(secondBall);
        this.pins = new Pins();
        pins.knockDown(firstBallKnockedDownPinCount);
        pins.knockDown(secondBallKnockedDownPinCount);

        // 세번째볼 처리
        Ball ball = new Ball(thirdBallKnockedDownPinCount);
        balls.add(ball);
        List<Pin> addPins = PinGenerator.generate();
        pins.add(addPins);
        pins.knockDown(thirdBallKnockedDownPinCount);
    }


    public void pitch() {
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

    public int score(){
        return balls.stream()
                .map(Ball::getKnockedDownPinCount)
                .reduce(0, Integer::sum);
    }

    public void pinsAdd(List<Pin> addPins) {
        pins.add(addPins);
    }

    public void addPitch() {
        List<Pin> addPins = PinGenerator.generate();
        pinsAdd(addPins);
        Ball ball = new Ball();
        ball.pitch(pins);
        balls.add(ball);
    }
}
