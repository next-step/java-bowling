package seul.bowling.domain;

import lombok.Getter;

public class FrameResult {
    @Getter
    private Score score;
    @Getter
    private FrameStatus status;

    public FrameResult() {
        this.status = FrameStatus.INIT;
        this.score = new Score();
    }

    public void addScore(int clearPin, boolean allClear) {
        boolean changeJudgementStatus = false;
        if (!endJudgmentStatus()) {
            this.status = status.judgmentStatus(allClear);
            changeJudgementStatus = true;
        }

        if (changeJudgementStatus) {
            this.score.addScore(clearPin, status.getBonusScoreCount());
        }
    }

    public void addCumulativeScore(int score) {
        this.score.addCumulativeScore(score);
    }

    public void addBonusScore(int bonusScore) {
        score.addBonusScore(bonusScore);
    }

    public boolean endScore() {
        return !availableAddBonusScore() && endJudgmentStatus();
    }

    public boolean availableAddBonusScore() {
        return !score.bonusScoreCountIsEmpty();
    }

    public boolean endJudgmentStatus() {
        return status.endStatus();
    }

    public int bonusPlay() {
        return status.getBonusPlay();
    }

    public boolean isStrike() {
        return status.isStrike();
    }

    public int getToTalScore() {
        return this.score.getScore();
    }
}
