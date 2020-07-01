package bowling.domain;

public abstract class Frame {
    public static final int WAITING_CALCULATION = -1;

    protected States states;

    abstract void bowl(int previousFallenPin, int currentFallenPin);
    abstract boolean isEndFrame();
    abstract boolean isEndGame();
    abstract Frame getNextFrame(int frameNumber);
    abstract int getScore();
    public abstract States getStates();
    abstract int calculateAdditionalScore(Score score);
    boolean isCalculateScore() {
        return getScore() != WAITING_CALCULATION;
    }
}
