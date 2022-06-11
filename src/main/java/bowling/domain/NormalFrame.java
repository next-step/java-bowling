package bowling.domain;

class NormalFrame extends Frame {
    private static final int NOT_COMPLETED_CALCULATION = 0;
    private static final int CALCULATE_TWICE = 2;
    private static final int CALCULATE_ONCE = 1;

    NormalFrame() {
        this.states = new States();
        this.next = null;
    }

    @Override
    public void delivery(int countOfPins) {
        if (states.firstBowl(countOfPins)) {
            return;
        }
        states.secondBowl(countOfPins);
    }

    @Override
    public boolean additionallyDeliverable() {
        return states.additionallyDeliverable();
    }

    @Override
    public int getScore() {
        if ((StateEnum.isFirstBowl(firstState()) || StateEnum.isFirstBowl(firstState())) && StateEnum.isReady(secondState())) {
            return 0;
        }

        Score score = states.createScore();
        if (score.canCalculateScore()) {
            return score.getScore();
        }

        return calculateAdditionalScore(score);
    }


    private int calculateAdditionalScore(Score beforeScore) {
        Score beforeBowl = null;
        if (incalculable(next.getFirstState())) {
            return NOT_COMPLETED_CALCULATION;
        }

        if (beforeScore.getLeft() == CALCULATE_ONCE) {
            beforeBowl = beforeScore.bowl(next.getFirstState().countOfPins);
        }

        if (beforeScore.getLeft() == CALCULATE_TWICE) {
            beforeBowl = calculateTwice(beforeScore);
        }

        if (beforeBowl == null) {
            return next.getScore();
        }

        return beforeBowl.getScore();
    }

    private Score calculateTwice(Score beforeScore) {
        Score beforeBowl = beforeScore.bowl(next.getFirstState().countOfPins);
        return addTwice(beforeBowl);
    }

    private Score addTwice(Score beforeBowl) {
        if (StateEnum.isReady(next.getSecondState())) {
            return secondAddition(beforeBowl);
        }
        return bowl(beforeBowl, next.getSecondState(), next.getSecondState().countOfPins);
    }

    private Score secondAddition(Score beforeBowl) {
        if (next.next == null) {
            return bowl(beforeBowl, next.getSecondState(), next.getSecondState().countOfPins);
        }
        return bowl(beforeBowl, next.next.getFirstState(), next.next.getFirstState().countOfPins);
    }

    private boolean incalculable(State state) {
        return StateEnum.isReady(state);
    }

    private Score bowl(Score beforeBowl, State state, int countOfPins) {
        if (incalculable(state)) {
            return new Score();
        }
        return beforeBowl.bowl(countOfPins);
    }

    State firstState() {
        return states.getFirstState();
    }

    State secondState() {
        return states.getSecondState();
    }
}
