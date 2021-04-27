package bowling.domain.concrete.frame.state;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Score;
import bowling.domain.engine.frame.state.CannotCompleteScoreException;
import bowling.domain.engine.frame.state.CannotInitializeScoreException;
import bowling.domain.engine.frame.state.State;
import bowling.dto.RollResultsDto;
import bowling.dto.StateExporter;

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
        return StateExporter.READY.export(RollResultsDto.empty());
    }

    @Override
    public Score addScoreTo(Score score) {
        throw new CannotCompleteScoreException();
    }

    @Override
    public Score createScore() {
        throw new CannotInitializeScoreException();
    }

}
