package bowling.domain.state;

public class FullFrameState {
    private AbstractState firstHalfFrameState;
    private AbstractState secondHalfFrameState;

    public FullFrameState() {
        this.firstHalfFrameState = new Ready();
        this.secondHalfFrameState = new Ready();
    }

    public boolean firstBowl(int countOfFallenPins) {
        if (StateEnum.isReady(firstHalfFrameState)) {
            firstHalfFrameState = firstHalfFrameState.bowl(countOfFallenPins);
            return true;
        }
        return false;
    }

    public boolean secondBowl(int countOfFallenPins) {
        if (StateEnum.isReady(secondHalfFrameState)) {
            secondHalfFrameState = firstHalfFrameState.bowl(countOfFallenPins);
            return true;
        }
        return false;
    }

    public boolean FinalFrameBowl(int countOfFallenPins) {
        if (firstBowl(countOfFallenPins)) {
            return true;
        }

        if (StateEnum.isStrike(firstHalfFrameState) && StateEnum.isReady(secondHalfFrameState)) {
            secondHalfFrameState = secondHalfFrameState.bowl(countOfFallenPins);
            return true;
        }

        return secondBowl(countOfFallenPins);
    }

    public boolean capableOfAdditionalBowling() {
        return StateEnum.isRunning(firstHalfFrameState) && StateEnum.isReady(secondHalfFrameState);
    }

    public boolean capableOfFinalAdditionalBowling() {
        return StateEnum.isStrike(firstHalfFrameState) || StateEnum.isSpare(secondHalfFrameState) || StateEnum.isReady(secondHalfFrameState);
    }

    public AbstractState getFirstHalfFrameState() {
        return firstHalfFrameState;
    }

    public int fallenPinsCountOfFirstFrame() {
        return firstHalfFrameState.getFallenPins();
    }

    public String symbolOfFirstFrame() {
        return firstHalfFrameState.getSymbol();
    }

    public AbstractState getSecondHalfFrameState() {
        return secondHalfFrameState;
    }

    public int fallenPinsCountOfSecondFrame() {
        return secondHalfFrameState.getFallenPins();
    }

    public String symbolOfSecondFrame() {
        return secondHalfFrameState.getSymbol();
    }

    public AbstractState secondFrameBowl(int fallenPinsCount) {
        return secondHalfFrameState.bowl(fallenPinsCount);
    }
}
