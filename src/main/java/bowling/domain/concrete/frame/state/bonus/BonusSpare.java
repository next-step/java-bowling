package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.bonus.BonusFinished;
import bowling.domain.engine.roll.RollResult;

public class BonusSpare extends BonusFinished {

    private final RollResult firstRollResult;
    private final RollResult secondRollResult;

    public BonusSpare(State beforeState,
                      RollResult firstRollResult, RollResult secondRollResult) {
        super(beforeState);
        validate(firstRollResult, secondRollResult);
        this.firstRollResult = firstRollResult;
        this.secondRollResult = secondRollResult;
    }

    private void validate(RollResult firstRoll, RollResult secondRoll) {
        validateIfFirstRollIsStrike(firstRoll);
        validateTwoResultsClearFrame(firstRoll, secondRoll);
    }

    private void validateIfFirstRollIsStrike(RollResult firstRoll) {
        if (firstRoll.isClear()) {
            throw new IllegalArgumentException("첫 번째 투구가 스트라이크면 Spare 상태가 아닙니다.");
        }
    }

    private void validateTwoResultsClearFrame(RollResult firstRoll, RollResult secondRoll) {
        if (!firstRoll.isClearWith(secondRoll)) {
            throw new IllegalArgumentException("두 번째 투구까지 모든 핀을 쓰러트리지 못했다면 Spare 상태가 아닙니다.");
        }
    }

    @Override
    public String export() {
        return String.join("|", super.export(), firstRollResult.export(), "/");
    }
}
