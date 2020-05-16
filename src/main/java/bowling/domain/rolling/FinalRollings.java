package bowling.domain.rolling;

import bowling.domain.frame.State;

public class FinalRollings {

    private NormalRollings normalRollings;
    private Rolling bonusRolling;

    private FinalRollings() {
        this.normalRollings = NormalRollings.init();
    }

    public static FinalRollings init() {
        return new FinalRollings();
    }

    public void roll(int pinCount) {
        if (normalRollings.isRollingPossible()) {
            normalRollings.roll(pinCount);
            return;
        }

        if (normalRollings.isStrikeOrSpare()) {
            setBonusRolling(pinCount);
        }
    }

    private void setBonusRolling(int pinCount) {
        State state = State.valueOf(pinCount);
        bonusRolling = new Rolling(state, pinCount);
    }

    public boolean isRollingPossible() {
        if (bonusRolling != null) {
            return false;
        }

        return normalRollings.isRollingPossible() || normalRollings.isStrikeOrSpare();
    }
}
