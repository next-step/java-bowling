package bowling.domain.concrete.frame.state;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Score;
import bowling.domain.engine.frame.UnavailableScore;
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
    public Score createScore() {
        return UnavailableScore.of(0);
    }

    @Override
    public Score addScoreTo(Score score) {
        return UnavailableScore.of(score.getValue());
    }

}
