package bowling.domain;

import bowling.type.BowlingScore;
import bowling.type.PlayStatus;

public class NormalFrame {
    private final BowlingScore bowlingScore;
    private final PinScore pinScore;
    private PlayStatus playStatus;

    public NormalFrame(PinScore pinScore) {
        this.pinScore = pinScore;
        if(pinScore.isStrike()) {
            this.bowlingScore = BowlingScore.STRIKE;
            this.playStatus = PlayStatus.END;
            return;
        }
        this.bowlingScore = BowlingScore.NONE;
        this.playStatus = PlayStatus.IN_PROGRESS;
    }

    public void nextTry(){
        pinScore.next();
        playStatus = PlayStatus.END;
    }

    public boolean isStrike(){
        return bowlingScore == BowlingScore.STRIKE;
    }

    public BowlingScore getBowlingScore() {
        if(pinScore.isStrike()) {
            return BowlingScore.STRIKE;
        }
        return pinScore.getBowlingScore();
    }

    public boolean isProgress(){
        return PlayStatus.IN_PROGRESS == playStatus;
    }

    public int getFirstScore(){
        return pinScore.getFirst();
    }

    public int getSecondScore(){
        return pinScore.getSecond();
    }
}
