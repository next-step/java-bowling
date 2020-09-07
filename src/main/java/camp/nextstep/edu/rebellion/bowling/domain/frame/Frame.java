package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.score.BonusScore;
import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;
import camp.nextstep.edu.rebellion.bowling.domain.status.FrameStatus;
import camp.nextstep.edu.rebellion.bowling.domain.status.FrameStatusResolver;

public abstract class Frame {
    protected FrameScore frameScore;
    protected BonusScore bonusScore;
    protected Attempt attempt;
    protected Attempt bonusAttempt;

    protected Frame(int initAttempt) {
        this.frameScore = FrameScore.clear();
        this.bonusScore = BonusScore.clear();
        this.attempt = new Attempt(initAttempt);
        this.bonusAttempt = new Attempt(0);
    }

    protected abstract void assignScore(int hits);

    public void markScore(int hits) {
        assignScore(hits);
        attempt.tried();
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

    // meet end 조건을 다르게 가야 함
    public boolean meetEnd() {
        return frameScore.isStrike() || !attempt.hasAttempt();
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
