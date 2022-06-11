package bowling.domain;

class States {
    private static final int CALCULATE_TWICE = 2;
    private static final int CALCULATE_ONCE = 1;
    private static final int NO_MORE_CALCULATION = 0;

    private State firstState;
    private State secondState;

    States() {
        this.firstState = new Ready();
        this.secondState = new Ready();
    }

    boolean finalDelivery(int countOfPins) {
        if (firstBowl(countOfPins)) {
            return true;
        }

        if (StateEnum.isStrike(firstState) && StateEnum.isReady(secondState)) {
            secondState = secondState.bowl(countOfPins);
            return true;
        }

        return secondBowl(countOfPins);
    }

    boolean firstBowl(int countOfPins) {
        if (StateEnum.isReady(firstState)) {
            firstState = firstState.bowl(countOfPins);
            return true;
        }
        return false;
    }

    boolean secondBowl(int countOfPins) {
        if (StateEnum.isReady(secondState)) {
            secondState = firstState.bowl(countOfPins);
            return true;
        }
        return false;
    }

    boolean additionallyDeliverable() {
        return (StateEnum.isReady(firstState) || StateEnum.isFirstBowl(firstState) || StateEnum.isGutter(firstState))
                && StateEnum.isReady(secondState);
    }

    boolean additionallyFinalDeliverable() {
        return StateEnum.isStrike(firstState) || StateEnum.isSpare(secondState) || StateEnum.isReady(secondState);
    }

    Score createScore() {
        int score = getThisFrameScore();
        if (StateEnum.isStrike(firstState)) {
            return new Score(score, CALCULATE_TWICE);
        }

        if (StateEnum.isSpare(secondState)) {
            return new Score(score, CALCULATE_ONCE);
        }

        return new Score(score, NO_MORE_CALCULATION);
    }

    private int getThisFrameScore() {
        return firstState.countOfPins + secondState.countOfPins;
    }

    State getFirstState() {
        return firstState;
    }

    State getSecondState() {
        return secondState;
    }
}
