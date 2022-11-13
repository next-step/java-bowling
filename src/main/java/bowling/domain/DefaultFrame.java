package bowling.domain;

import bowling.type.BowlingScore;
import bowling.type.PlayStatus;

public abstract class DefaultFrame implements Frame{
    protected int order = 1;
    private final Score score;
    private PlayStatus playStatus;

    public DefaultFrame(Score score) {
        this.score = score;
        if(score.isStrike()) {
            this.playStatus = PlayStatus.END;
            return;
        }
        this.playStatus = PlayStatus.IN_PROGRESS;
    }

    public DefaultFrame(Score score, int order) {
        this.score = score;
        this.order = order;
        if(score.isStrike()) {
            this.playStatus = PlayStatus.END;
            return;
        }
        this.playStatus = PlayStatus.IN_PROGRESS;
    }

    public void nextTry(){
        score.next();
        playStatus = PlayStatus.END;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int getLatestScore() {
        return score.getLatest();
    }

    public boolean isStrike(){
        return score.getBowlingScore() == BowlingScore.STRIKE;
    }

    public BowlingScore getBowlingScore() {
        return score.getBowlingScore();
    }

    public boolean isProgress(){
        return PlayStatus.IN_PROGRESS == playStatus;
    }

    public int getFirstScore(){
        return score.getFirst();
    }

    public int getSecondScore(){
        return score.getSecond();
    }
}
