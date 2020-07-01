package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.List;

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

        if (pitch.add(pin)) {
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
    public List<Shot> getShotHistory() {
        List<Shot> shotHistory = pitch.getShotHistory();
        if (isBonusPitch) {
            shotHistory.addAll(bonusPitch.getShotHistory());
        }
        return shotHistory;
    }

    @Override
    public boolean isGameEnd() {
        if (isBonusPitch) {
            return bonusPitch.getThrowCount() > IntegerUtils.ZERO;
        }
        return pitch.isPitchEnd();
    }
}
