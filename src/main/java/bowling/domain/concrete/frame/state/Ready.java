package bowling.domain.concrete.frame.state;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.roll.RollResult;
import bowling.dto.RollResultsDto;
import bowling.dto.StateDto;

public class Ready implements State {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State transit(RollResult rollResult) {
        if (rollResult.isClear()) {
            return new Strike(rollResult);
        }
        return new Playing(rollResult);
    }

    @Override
    public String export() {
        return StateDto.READY.export(RollResultsDto.empty());
    }
}
