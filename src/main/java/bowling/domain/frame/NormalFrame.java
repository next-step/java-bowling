package bowling.domain.frame;

import bowling.domain.exception.EndedFrameException;
import bowling.domain.exception.ExceedFallenPinsException;
import bowling.domain.pin.FallenPins;
import bowling.domain.pin.FallenPinsBucket;

public class NormalFrame implements Frame {

    private final FallenPinsBucket fallenPinsBucket = FallenPinsBucket.ofNormalSize();

    @Override
    public NormalFrame updateFrameState(FallenPins fallenPins) {
        if (isFinish()) {
            throw new EndedFrameException();
        }

        if (isFirstTurn()) {
            saveFirstTurn(fallenPins);
            return this;
        }

        validateSecondFallenPins(fallenPins);
        saveSecondTurn(fallenPins);

        return this;
    }

    @Override
    public boolean isFinish() {
        if (isFirstTurn()) {
            return false;
        }
        return hasStrike() || fallenPinsBucket.isFull();
    }

    @Override
    public FallenPins getFirstTurnResult() {
        return fallenPinsBucket.getFallenPins(0);
    }

    @Override
    public FallenPins getSecondTurnResult() {
        return fallenPinsBucket.getFallenPins(1);
    }

    private boolean hasStrike() {
        return getFirstTurnResult().isAllPinFallen();
    }

    private void validateSecondFallenPins(FallenPins fallenPins) {
        FallenPins firstFallenPins = fallenPinsBucket.getFallenPins(0);

        try {
            firstFallenPins.merge(fallenPins);
        } catch (Exception e) {
            throw new ExceedFallenPinsException();
        }
    }

    private boolean isFirstTurn() {
        return !fallenPinsBucket.isTurnFinished(0);
    }

    private void saveFirstTurn(FallenPins fallenPins) {
        fallenPinsBucket.saveFallenPins(fallenPins, 0);
    }

    private void saveSecondTurn(FallenPins fallenPins) {
        fallenPinsBucket.saveFallenPins(fallenPins, 1);
    }

}
