package bowling.domain.concrete.frame.state;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.state.Finished;
import bowling.dto.RollResultsDto;
import bowling.dto.StateExporter;

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
    public boolean canPromoteToBonusState() {
        return true;
    }

    @Override
    public String export() {
        return StateExporter.STRIKE.export(RollResultsDto.of(strikeRoll));
    }
}
