package bowling.domain.frame;

import bowling.domain.state.*;

public class FinalFrame extends AbstractFrame {
    private static final int NOT_COMPLETED_CALCULATION = 0;

    private AbstractState bonusState;

    FinalFrame() {
        super();
        this.bonusState = new Ready();
    }

    @Override
    public void bowl(int fallenPinsCount) {
        if (fullFrameState.FinalFrameBowl(fallenPinsCount)) {
            return;
        }

        if (StateEnum.isStrike(getSecondHalfFrameState()) || StateEnum.isSpare(getSecondHalfFrameState())) {
            bonusState = bonusState.bowl(fallenPinsCount);
            return;
        }

        if (StateEnum.isStrike(getFirstHalfFrameState())) {
            bonusState = fullFrameState.secondFrameBowl(fallenPinsCount);
        }
    }

    @Override
    public boolean capableOfAdditionalBowling() {
        return StateEnum.isReady(bonusState) && fullFrameState.capableOfFinalAdditionalBowling();
    }

    @Override
    public int getScore() {
        if ((StateEnum.isReady(getSecondHalfFrameState()) || StateEnum.isSpare(getSecondHalfFrameState()) || StateEnum.isStrike(getSecondHalfFrameState()))
                && StateEnum.isReady(bonusState)) {
            return NOT_COMPLETED_CALCULATION;
        }

        return fallenPinsCountOfFirstFrame() + fallenPinsCountOfSecondFrame() + bonusState.getFallenPins();
    }

    AbstractState getBonusState() {
        return bonusState;
    }

    public String symbolOfBonusFrame() {
        return bonusState.getSymbol();
    }
}
