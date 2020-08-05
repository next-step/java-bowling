package bowling.domain.rolling;

import bowling.domain.frame.Score;
import bowling.domain.state.StateFormat;

import java.util.List;
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

        setBonusRolling(pinCount);
    }

    private void setBonusRolling(int pinCount) {
        StateFormat stateFormat = StateFormat.valueOf(pinCount);
        bonusRolling = Optional.of(new Rolling(stateFormat, pinCount));
    }

    @Override
    public boolean isState(StateFormat stateFormat) {
        return bonusRolling.map(rolling -> rolling.isState(stateFormat))
                .orElseGet(() -> normalRollings.isState(stateFormat));
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
    public boolean isRollingStarted() {
        return !normalRollings.isRollingStarted() ||
                isRollingPossible();
    }

    @Override
    public List<String> getStates() {
        List<String> states = normalRollings.getStates();

        bonusRolling.ifPresent(
                rolling -> states.add(rolling.getStateFormatValue())
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
