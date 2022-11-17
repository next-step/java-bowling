package bowling.domain.frame;

import bowling.domain.exception.EndedFrameException;
import bowling.domain.pin.FallenPins;
import bowling.domain.pin.FallenPinsBucket;
import java.util.Optional;

public class FinalFrame implements Frame {

    private final FallenPinsBucket fallenPinsBucket = FallenPinsBucket.ofFinalSize();

    @Override
    public Frame updateFrameState(FallenPins fallenPins) {
        if (isFinish()) {
            throw new EndedFrameException();
        }

        if (isFirstTurn()) {
            saveFirstTurn(fallenPins);
            return this;
        }

        if (isSecondTurn()) {
            saveSecondTurn(fallenPins);
            return this;
        }

        if (hasBonus()) {
            saveBonusTurn(fallenPins);
        }

        return this;
    }

    @Override
    public FallenPins getFirstTurnResult() {
        return fallenPinsBucket.getFallenPins(0);
    }

    @Override
    public FallenPins getSecondTurnResult() {
        return fallenPinsBucket.getFallenPins(1);
    }

    public FallenPins getBonusTurnResult() {
        return fallenPinsBucket.getFallenPins(2);
    }

    @Override
    public boolean isFinish() {
        if (hasBonus()) {
            return fallenPinsBucket.isFull();
        }
        return isNormalRoundFinish();
    }

    private boolean isNormalRoundFinish() {
        return fallenPinsBucket.isTurnFinished(0) && fallenPinsBucket.isTurnFinished(1);
    }

    private boolean hasBonus() {
        if (hasStrike()) {
            return true;
        }
        return hasSpare();
    }

    private boolean hasStrike() {
        return fallenPinsBucket.isTurnFinished(0)
                && Optional.of(getFirstTurnResult().isAllPinFallen())
                .orElse(false);
    }

    private boolean hasSpare() {
        FallenPins firstFallenPins = getFirstTurnResult();
        FallenPins secondFallenPins = getSecondTurnResult();

        return !isFirstTurn()
                && !isSecondTurn()
                && firstFallenPins.merge(secondFallenPins).isAllPinFallen();
    }

    private boolean isFirstTurn() {
        return !fallenPinsBucket.isTurnFinished(0);
    }

    private boolean isSecondTurn() {
        return !fallenPinsBucket.isTurnFinished(1);
    }

    private void saveFirstTurn(FallenPins fallenPins) {
        fallenPinsBucket.saveFallenPins(fallenPins, 0);
    }

    private void saveSecondTurn(FallenPins fallenPins) {
        fallenPinsBucket.saveFallenPins(fallenPins, 1);
    }

    private void saveBonusTurn(FallenPins fallenPins) {
        fallenPinsBucket.saveFallenPins(fallenPins, 2);
    }

}
