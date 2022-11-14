package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Balls {
    private List<Ball> balls = new ArrayList<>();

    private Pins pins;

    public Balls() {
        Ball firstBall = new Ball();
        Ball secondBall = new Ball();
        this.balls.add(firstBall);
        this.balls.add(secondBall);
        this.pins = new Pins();
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
        this(firstBallKnockedDownPinCount, secondBallKnockedDownPinCount);
        // 세번째볼 처리
        Ball ball = new Ball(thirdBallKnockedDownPinCount);
        balls.add(ball);
        List<Pin> addPins = PinGenerator.generate();
        pins.add(addPins);
        pins.knockDown(thirdBallKnockedDownPinCount);
    }


    public void pitch(int knockedDownPinCount) {
        if (pitchEnd()) {
            throw new RuntimeException("더 이상 공을 던질수 없습니다.");
        }
        Ball ball = findNotPitchedBall();
        pins.knockDown(knockedDownPinCount);
        ball.pitch(knockedDownPinCount);

        if (isFirstBallStrike()) {
            balls.get(1).pitch(0);
        }
    }

    private Ball findNotPitchedBall() {
        return balls.stream()
                .filter(Ball::isNotPitched)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("모든공을 던졌습니다."));
    }

    public int getKnockedDownPinCount(int index) {
        return balls.get(index).getKnockedDownPinCount();
    }

    public int score() {
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
        balls.add(ball);
    }

    public boolean pitchEnd() {
        return pins.standingPinCount() == 0 || pitchedAllBalls();
    }

    public boolean pitchedAllBalls() {
        return balls.stream()
                .allMatch(Ball::isPitched);
    }

    public boolean pitchedAnyBalls() {
        return balls.stream()
                .anyMatch(Ball::isPitched);
    }

    public int pitchedBallCount() {
        return (int) balls.stream()
                .filter(Ball::isPitched)
                .count();
    }

    public String scoringText() {
        if (isFirstBallStrike() || pitchedBallCount() == 1) {
            return isFirstBallScoreText();
        }
        return isFirstBallScoreText() + "|" + isSecondBallScoreText();
    }

    private boolean isFirstBallStrike() {
        int firstBallKnockedDownPinCount = getKnockedDownPinCount(0);
        return firstBallKnockedDownPinCount == 10;
    }

    private String isFirstBallScoreText() {
        int firstBallKnockedDownPinCount = getKnockedDownPinCount(0);
        return ScoreRull.getSymbolOrScore(firstBallKnockedDownPinCount, true);
    }

    private String isSecondBallScoreText() {
        int firstBallKnockedDownPinCount = getKnockedDownPinCount(0);
        int secondBallKnockedDownPinCount = getKnockedDownPinCount(1);
        int totalKnockedDownPinCount = firstBallKnockedDownPinCount + secondBallKnockedDownPinCount;
        if (totalKnockedDownPinCount == 10) {
            return ScoreRull.getSymbolOrScore(totalKnockedDownPinCount, false);
        }
        return ScoreRull.getSymbolOrScore(secondBallKnockedDownPinCount, false);
    }

}
