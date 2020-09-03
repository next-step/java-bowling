package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;
import camp.nextstep.edu.rebellion.bowling.domain.status.FrameStatus;
import camp.nextstep.edu.rebellion.bowling.domain.status.FrameStatusResolver;

public abstract class Frame {
    protected FrameScore frameScore;
    protected Attempt attempt;

    protected Frame(int initAttempt) {
        this.frameScore = new FrameScore();
        this.attempt = new Attempt(initAttempt);
    }

    protected abstract void assignScore(int hits);

    public void markScore(int hits) {
        assignScore(hits);
        attempt.tried();
    }

    public boolean meetEnd() {
        return frameScore.isStrike() || !attempt.hasAttempt();
    }

    public boolean isStarted() {
        return !attempt.isFirstAttempt();
    }

    public FrameStatus getStatus() {
        return FrameStatusResolver.resolve(this);
    }

    public FrameScore getFrameScore() {
        return frameScore;
    }
}
