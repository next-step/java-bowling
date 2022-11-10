package bowling.domain;

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

    public void play(){
        balls.pitch(pins);
    }

    public String scoringText() {
        int firstBallKnockedDownPinCount = balls.getKnockedDownPinCount(0);
        if (firstBallKnockedDownPinCount == 10) {
            return ScoreRull.getSymbolOrScore(firstBallKnockedDownPinCount, true);
        }
        String result = ScoreRull.getSymbolOrScore(firstBallKnockedDownPinCount, false) + "|";
        int secondBallKnockedDownPinCount = balls.getKnockedDownPinCount(1);
        int totalKnockedDownPinCount = firstBallKnockedDownPinCount + secondBallKnockedDownPinCount;

        if(totalKnockedDownPinCount == 10) {
            return result + ScoreRull.getSymbolOrScore(totalKnockedDownPinCount, false);
        }
        return result + ScoreRull.getSymbolOrScore(secondBallKnockedDownPinCount, false);
    }
}
