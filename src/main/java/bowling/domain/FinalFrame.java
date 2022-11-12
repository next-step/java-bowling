package bowling.domain;

public class FinalFrame extends Frame {

    public FinalFrame(int round) {
        this(round, new Balls(0, 0));
    }

    public FinalFrame(int round, Balls balls) {
        this.round = round;
        this.balls = balls;
    }

    @Override
    public void play() {
        balls.pitch();
        if (balls.score() == 10) {
            balls.addPitch();
        }
    }

    @Override
    public String scoringText() {
        String result = scoringTextNormalFrame();
        if (balls.size() < 3) {
            return result;
        }
        int thirdBallKnockedDownPinCount = balls.getKnockedDownPinCount(2);
        return result + "|" + ScoreRull.getSymbolOrScore(thirdBallKnockedDownPinCount, true);
    }
}
