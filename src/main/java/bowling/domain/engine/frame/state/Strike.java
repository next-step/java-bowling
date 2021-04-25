package bowling.domain.engine.frame.state;

import bowling.domain.engine.roll.RollResult;

public class Strike extends Finished {

    private final RollResult strikeRoll;

    public Strike(RollResult rollResult) {
        validate(rollResult);
        this.strikeRoll = rollResult;
    }

    private void validate(RollResult rollResult) {
        if (!rollResult.isClear()) {
            throw new IllegalArgumentException("스트라이크를 쳤을 때만 Strike 상태를 만들 수 있습니다.");
        }
    }

    @Override
    public String export() {
        return "X";
    }
}
