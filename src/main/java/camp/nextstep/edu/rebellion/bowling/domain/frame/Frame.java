package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;
import camp.nextstep.edu.rebellion.bowling.domain.status.FrameStatus;
import camp.nextstep.edu.rebellion.bowling.domain.status.FrameStatusResolver;

public abstract class Frame {
    protected FrameScore frameScore;
    protected Attempt attempt;
    protected Bonus bonus;

    protected Frame(FrameScore frameScore, int initAttempt) {
        this.frameScore = frameScore;
        this.attempt = new Attempt(initAttempt);
        this.bonus = Bonus.clear();
    }

    abstract void adjustAttempt();
    public abstract boolean match(FrameType type);
    public abstract boolean canCalculateScore();

    public void markScore(int hits) {
        assignScore(hits);
        adjustAttempt();
    }

    private void assignScore(int hits) {
        if (attempt.isFirstAttempt()) {
            frameScore.markFirst(hits);
            return;
        }
        frameScore.markLast(hits);
    }

    public void markBonus(int hits){
        bonus.markScore(hits);
    }

    public void makeBonusChance() {
        bonus.giveChance(frameScore.getTryAttempt());
    }

    public boolean meetEnd() {
        return !attempt.hasAttempt();
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

    public int getHitsScore() {
        return frameScore.getHitsScore() + bonus.getHitsScore();
    }
}
