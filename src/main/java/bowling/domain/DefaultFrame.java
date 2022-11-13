package bowling.domain;

import bowling.type.BowlingScore;
import bowling.type.PlayStatus;

public abstract class DefaultFrame implements Frame{

    protected int order = 1;
    protected GeneralScore generalScore;
    protected PlayStatus playStatus;

    public DefaultFrame(GeneralScore generalScore) {
        this.generalScore = generalScore;
        this.playStatus = decideEnd(generalScore);
    }

    public DefaultFrame(GeneralScore generalScore, int order) {
        this.generalScore = generalScore;
        this.order = order;
        this.playStatus = decideEnd(generalScore);
    }

    private PlayStatus decideEnd(GeneralScore generalScore) {
        if (generalScore.isStrike()) {
            return PlayStatus.END;
        }
        return PlayStatus.IN_PROGRESS;
    }

    public void nextTry(){
        generalScore.next();
        playStatus = PlayStatus.END;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int getLatestScore() {
        return generalScore.getLatest();
    }

    public boolean isStrike(){
        return BowlingScore.STRIKE == BowlingScore.from(generalScore);
    }

    public BowlingScore getBowlingScore() {
        return BowlingScore.from(generalScore);
    }

    public boolean isProgress(){
        return PlayStatus.IN_PROGRESS == playStatus;
    }

    public int getFirstScore(){
        return generalScore.getFirst();
    }

    public int getSecondScore(){
        return generalScore.getSecond();
    }
}
