package bowling.domain;

import java.util.List;

public class Frame {
    private int round;

    private Balls balls;

    private Pins pins;

    public Frame(int round) {
        this(round, 0, 0);
    }

    public Frame(int round, int firstBallKnockedDownPinCount, int secondBallKnockedDownPinCount) {
        this.round = round;
        this.balls = new Balls(firstBallKnockedDownPinCount, secondBallKnockedDownPinCount);
        this.pins = new Pins();
        pins.knockDown(firstBallKnockedDownPinCount);
        pins.knockDown(secondBallKnockedDownPinCount);
    }

    public Frame(int round, int firstBallKnockedDownPinCount, int secondBallKnockedDownPinCount, int thirdBallKnockedDownPinCount) {
        this.round = round;
        this.balls = new Balls(firstBallKnockedDownPinCount, secondBallKnockedDownPinCount);
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

    public void play() {
        balls.pitch(pins);
        if (pins.knockDownCount() == 10 && round == 10) {
            List<Pin> addPins = PinGenerator.generate();
            pins.add(addPins);
            Ball ball = new Ball();
            ball.pitch(pins);
            balls.add(ball);
        }
    }

    public String scoringText() {
        String result = scoringTextOneToNineRound();
        if (balls.size() < 3) {
            return result;
        }
        int thirdBallKnockedDownPinCount = balls.getKnockedDownPinCount(2);
        return result + "|" + ScoreRull.getSymbolOrScore(thirdBallKnockedDownPinCount, true);
    }

    private String scoringTextOneToNineRound() {
        int firstBallKnockedDownPinCount = balls.getKnockedDownPinCount(0);
        if (firstBallKnockedDownPinCount == 10) {
            return ScoreRull.getSymbolOrScore(firstBallKnockedDownPinCount, true);
        }
        String result = ScoreRull.getSymbolOrScore(firstBallKnockedDownPinCount, false) + "|";
        int secondBallKnockedDownPinCount = balls.getKnockedDownPinCount(1);
        int totalKnockedDownPinCount = firstBallKnockedDownPinCount + secondBallKnockedDownPinCount;

        if (totalKnockedDownPinCount == 10) {
            return result + ScoreRull.getSymbolOrScore(totalKnockedDownPinCount, false);
        }
        return result + ScoreRull.getSymbolOrScore(secondBallKnockedDownPinCount, false);
    }

    public int score() {
        return pins.knockDownCount();
    }
}
