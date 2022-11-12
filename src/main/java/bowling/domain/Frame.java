package bowling.domain;

import java.util.List;

public abstract class Frame {
    protected int round;

    protected Balls balls;

    public Frame() {
    }

    public abstract void play();

    public abstract String scoringText();

    public String scoringTextNormalFrame() {
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
        return balls.score();
    }
}
