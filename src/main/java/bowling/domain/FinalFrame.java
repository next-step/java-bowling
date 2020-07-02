package bowling.domain;

import bowling.common.IntegerUtils;

public class FinalFrame extends Frame {
    private boolean isBonusPitch;
    private Pitch bonusPitch;

    public FinalFrame() {
        this.frameNo = FINAL_FRAME;
        this.pitch = new Pitch();
        this.isBonusPitch = false;
        this.bonusPitch = new Pitch();
    }

    @Override
    public State bowling(Pin pin) {
        if (isBonusPitch) {
            return bowlingBonus(pin);
        }

        pitch.add(pin);
        if (pitch.isFinish()) {
            return isNextBonusPitch();
        }
        return State.NotFinish;
    }

    private State bowlingBonus(Pin pin) {
        bonusPitch = new Pitch();
        bonusPitch.add(pin);
        return State.Finish;
    }

    private State isNextBonusPitch() {
        if (pitch.getRemain() == IntegerUtils.ZERO) {
            isBonusPitch = true;
            return State.Bonus;
        }
        return State.Finish;
    }

    @Override
    public ShotHistory getShotHistory() {
        ShotHistory shotHistory = pitch.getShotHistory();
        if (isBonusPitch) {
            shotHistory.add(bonusPitch.getShotHistory());
        }
        return shotHistory;
    }

    @Override
    public boolean isGameEnd() {
        if (isBonusPitch) {
            return bonusPitch.getThrowCount() > IntegerUtils.ZERO;
        }
        return pitch.isFinish();
    }
}
