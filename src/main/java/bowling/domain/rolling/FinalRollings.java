package bowling.domain.rolling;

import bowling.domain.frame.State;

public class FinalRollings implements Rollings {

    private NormalRollings normalRollings;
    private Rolling bonusRolling;

    private FinalRollings() {
        this.normalRollings = NormalRollings.init();
    }

    public static FinalRollings init() {
        return new FinalRollings();
    }

    @Override
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

    @Override
    public boolean isRollingPossible() {
        if (bonusRolling != null) {
            return false;
        }

        return normalRollings.isRollingPossible() || normalRollings.isStrikeOrSpare();
    }
}
