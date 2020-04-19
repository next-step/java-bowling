package seul.bowling.domain;

import lombok.Getter;

public class LastFrame extends Frame {
    @Getter
    private int bonusPlay;

    protected LastFrame(int index) {
        super(index);

        if (index == LAST_FRAME_INDEX && !isBonusPlay()) {
            this.bonusPlay = status.getBonusPlay();
        }

        if (isBonusPlay() && endDefaultPlay()) {
            this.bonusPlay--;
        }
    }

    @Override
    public void addPins(int clearPin) {
        addPins(clearPin, isBonusPlay());

        if (!isBonusPlay()) {
            this.bonusPlay = status.getBonusPlay();
        }

        if (isBonusPlay() && endDefaultPlay()) {
            this.bonusPlay--;
        }
    }

    @Override
    public boolean isLastFame() {
        return true;
    }

    @Override
    public boolean endFrame() {
        return status.endJudgmentStatus() && this.bonusPlay == ZERO;
    }

    private boolean endDefaultPlay() {
        return status.pinsEndDefaultPlayCount();
    }

    private boolean isBonusPlay() {
        return this.bonusPlay > ZERO;
    }
}
