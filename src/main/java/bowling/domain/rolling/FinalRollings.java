package bowling.domain.rolling;

import bowling.domain.frame.Score;

import java.util.List;
import java.util.Objects;

public class FinalRollings implements Rollings {

    private final NormalRollings normalRollings;
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
    public boolean isState(State state) {
        if (bonusRolling != null) {
            return bonusRolling.getState() == state;
        }

        return normalRollings.isState(state);
    }

    @Override
    public boolean isRollingPossible() {
        if (bonusRolling != null) {
            return false;
        }

        return normalRollings.isRollingPossible() || normalRollings.isStrikeOrSpare();
    }

    @Override
    public List<String> getStates() {
        List<String> states = normalRollings.getStates();

        if (bonusRolling != null) {
            states.add(bonusRolling.getStateFormat());
        }

        return states;
    }

    @Override
    public int getLastScore() {
        if (!Objects.isNull(bonusRolling)) {
            return bonusRolling.getKnockedDownPinCount();
        }

        return normalRollings.getLastScore();
    }


    @Override
    public int calculateScore() {
        return normalRollings.calculateScore() + bonusRolling.getKnockedDownPinCount();
    }

    @Override
    public void calculateAdditionalScore(Score score) {

    }
}
