package bowling.domain;

import bowling.type.BowlingScore;
import bowling.type.PlayStatus;

public abstract class DefaultFrame implements Frame {

    protected int order = 1;
    protected Scores scores;
    protected PlayStatus playStatus;

    public DefaultFrame(Scores scores) {
        this.scores = scores;
        this.playStatus = decideEnd(scores);
    }

    public DefaultFrame(Scores scores, int order) {
        this.scores = scores;
        this.order = order;
        this.playStatus = decideEnd(scores);
    }

    private PlayStatus decideEnd(Scores scores) {
        if (scores.isStrike()) {
            return PlayStatus.END;
        }
        return PlayStatus.IN_PROGRESS;
    }

    public void nextTry() {
        scores.next();
        playStatus = PlayStatus.END;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int getLatestScore() {
        return scores.getLatest();
    }

    public BowlingScore getBowlingScore() {
        return BowlingScore.from(scores);
    }

    public boolean isProgress() {
        return PlayStatus.IN_PROGRESS == playStatus;
    }

    public int getFirstScore() {
        return scores.getFirst();
    }

    public int getSecondScore() {
        return scores.getSecond();
    }

    public int getThirdScore() {
        return scores.getThird();
    }

    @Override
    public int getScoreSize() {
        return scores.size();
    }
}
