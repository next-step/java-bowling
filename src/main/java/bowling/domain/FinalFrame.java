package bowling.domain;

public class FinalFrame extends Frame {
    private boolean hasBonusPitch = false;

    public FinalFrame() {
        this(FINAL_FRAME_NUMBER);
    }

    public FinalFrame(int round) {
        this(round, new Balls());
    }

    public FinalFrame(int round, Balls balls) {
        this.round = round;
        this.balls = balls;
    }

    @Override
    public void play(int knockedDownPinCount) {
        balls.pitch(knockedDownPinCount);
        if (balls.score() == 10 && !hasBonusPitch) {
            balls.addPitch();
            hasBonusPitch = true;
        }
    }

    @Override
    public String scoringText() {
        String result = scoringTextNormalFrame();
        if (balls.pitchedBallCount() < 3) {
            return result;
        }
        int thirdBallKnockedDownPinCount = balls.getKnockedDownPinCount(2);
        return result + "|" + ScoreRull.getSymbolOrScore(thirdBallKnockedDownPinCount, true);
    }

    @Override
    public Frame createNextFrame() {
        throw new UnsupportedOperationException();
    }
}
