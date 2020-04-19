package seul.bowling.domain;

import lombok.Getter;

public class LastFrame extends Frame{
    @Getter
    private int bonusPlay;

    protected LastFrame(int index) {
        super(index);

        if (index == LAST_FRAME_INDEX && !isBonusPlay()) {
            this.bonusPlay = result.bonusPlay();
        }

        if (isBonusPlay() && endDefaultPlay()) {
            this.bonusPlay--;
        }
    }

    @Override
    public void addPins(int clearPin) {
        pins.addPin(clearPin, isBonusPlay());
        result.addScore(clearPin, pins.allClear());

        if (!isBonusPlay()) {
            this.bonusPlay = result.bonusPlay();
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
        return result.endJudgmentStatus() && this.bonusPlay == ZERO;
    }

    private boolean endDefaultPlay() {
        return pins.endDefaultPlayCount(result.isStrike());
    }

    private boolean isBonusPlay() {
        return this.bonusPlay > ZERO;
    }
}
