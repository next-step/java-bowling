package bowling.domain.rolling;

import bowling.domain.frame.Score;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FinalRollings implements Rollings {

    private final NormalRollings normalRollings;
    private Optional<Rolling> bonusRolling;

    private FinalRollings() {
        this.normalRollings = NormalRollings.init();
        this.bonusRolling = Optional.empty();
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
        bonusRolling = Optional.of(new Rolling(state, pinCount));
    }

    @Override
    public boolean isState(State state) {
        return bonusRolling.map(rolling -> rolling.isState(state))
                .orElseGet(() -> normalRollings.isState(state));
    }

    @Override
    public boolean isRollingPossible() {
        if (bonusRolling.isPresent()) {
            return false;
        }

        return normalRollings.isRollingPossible() ||
                normalRollings.isStrikeOrSpare();
    }

    @Override
    public List<String> getStates() {
        List<String> states = normalRollings.getStates();

        bonusRolling.ifPresent(
                rolling -> states.add(rolling.getStateFormat())
        );

        return states;
    }

    @Override
    public int calculateScore() {
        return bonusRolling.map(
                rolling -> normalRollings.calculateScore() + rolling.getKnockedDownPinCount())
                .orElseGet(normalRollings::calculateScore);

    }

    @Override
    public void calculateAdditionalScore(Score score) {
        normalRollings.calculateAdditionalScore(score);

        if (!score.isCalculateDone() && bonusRolling.isPresent()) {
            score.calculateAdditional(bonusRolling.get().getKnockedDownPinCount());
        }
    }
}
