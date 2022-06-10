package bowling.domain;

public abstract class Frame {
    States states;
    Frame next;

    public abstract void delivery(int countOfPins);

    public abstract boolean additionallyDeliverable();

    public abstract int getScore();

    States getStates() {
        return states;
    }

    public State getFirstState() {
        return states.getFirstState();
    }

    public State getSecondState() {
        return states.getSecondState();
    }
}
