package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.score.BonusScore;
import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;
import camp.nextstep.edu.rebellion.bowling.domain.status.FrameStatus;
import camp.nextstep.edu.rebellion.bowling.domain.status.FrameStatusResolver;

public abstract class Frame {
    protected FrameScore frameScore;
    protected BonusScore bonusScore; // bonus 분리 필요함 score 라는게 필요 없음
    protected Attempt attempt;
    protected Attempt bonusAttempt;

    protected Frame(int initAttempt) {
        this.frameScore = FrameScore.clear();
        this.bonusScore = BonusScore.clear();
        this.attempt = new Attempt(initAttempt);
        this.bonusAttempt = new Attempt(0);
    }
    protected abstract void adjustAttempt();
    protected abstract boolean match(FrameType type);

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
        if (bonusAttempt.hasAttempt()) {
            bonusScore.mark(hits);
            bonusAttempt.tried();
        }
    }

    public void makeBonusChance() {
        if (frameScore.isStrike()) {
            this.bonusAttempt = Attempt.reset(2);
        }

        if (frameScore.isSpare()) {
            this.bonusAttempt = Attempt.reset(1);
        }
    }

    public boolean meetEnd() {
        return !attempt.hasAttempt();
    }

    public boolean canCalculateScore() {
        return meetEnd() && !bonusAttempt.hasAttempt();
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
        return frameScore.getHitsScore() + bonusScore.getHitsScore();
    }
}
