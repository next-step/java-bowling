package bowling.domain;

public class FinalFrame extends NormalFrame {
    private State bonusState;

    FinalFrame() {
        super();
        this.bonusState = new Ready();
    }

    @Override
    public void delivery(int countOfPins) {
        if (states.finalDelivery(countOfPins)) {
            return;
        }

        if (StateEnum.isStrike(secondState()) || StateEnum.isSpare(secondState())) {
            bonusState = bonusState.bowl(countOfPins);
            return;
        }

        if (StateEnum.isStrike(firstState())) {
            bonusState = states.getSecondState().bowl(countOfPins);
        }
    }

    @Override
    public boolean additionallyDeliverable() {
        return StateEnum.isReady(bonusState) && states.additionallyFinalDeliverable();
    }

    @Override
    public int getScore() {
        if ((StateEnum.isReady(secondState()) || StateEnum.isSpare(secondState()) || StateEnum.isStrike(secondState()))
                && StateEnum.isReady(bonusState)) {
            return 0;
        }

        return firstState().countOfPins + secondState().countOfPins + bonusState.countOfPins;
    }

    public State getBonusState() {
        return bonusState;
    }
}
