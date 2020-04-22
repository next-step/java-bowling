package seul.bowling.domain;

import seul.bowling.domain.status.Status;

public class LastFrame extends Frame {
    private static final int ZERO = 0;

    protected LastFrame(int index) {
        super(index);
    }

    private int bonusPlay;

    @Override
    public boolean isLastFame() {
        return true;
    }

    @Override
    public void addPins(int clearPin) {
        if (!endBonusPlay()) {
            bonusPlay--;
        }

        Status newStatus = status.addPins(clearPin);

        if (!newStatus.equalsStatus(status)) {
            this.status = newStatus;
        }
    }

    public boolean endFrame() {
        return status.end() && status.endCalculateScore();
    }

    private boolean endBonusPlay() {
        return this.bonusPlay <= ZERO;
    }
}
