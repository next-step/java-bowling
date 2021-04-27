package bowling.domain.concrete.frame.state;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Score;
import bowling.domain.engine.frame.state.CannotCompleteScoreException;
import bowling.domain.engine.frame.state.CannotInitializeScoreException;
import bowling.domain.engine.frame.state.State;
import bowling.dto.RollResultsDto;
import bowling.dto.StateExporter;

public class Playing implements State {

    private final RollResult firstRoll;

    public Playing(RollResult firstRoll) {
        this.firstRoll = firstRoll;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State transit(RollResult secondRoll) {
        validateTransit(secondRoll);

        if (firstRoll.isClearWith(secondRoll)) {
            return new Spare(firstRoll, secondRoll);
        }
        return new Miss(firstRoll, secondRoll);
    }

    private void validateTransit(RollResult secondRoll) {
        if(firstRoll.exceedMaximumPins(secondRoll)) {
            throw new IllegalArgumentException("두 번의 투구에서 쓰러트린 핀의 개수가 10 보다 클 수 없습니다.");
        }
    }

    @Override
    public String export() {
        return StateExporter.PLAYING.export(RollResultsDto.of(firstRoll));
    }

    @Override
    public Score addScoreTo(Score score) {
        Score addedScore = score.inject(firstRoll);

        if (!addedScore.isCalculationCompleted()) {
            throw new CannotCompleteScoreException();
        }

        return addedScore;
    }

    @Override
    public Score createScore() {
        throw new CannotInitializeScoreException();
    }
}
