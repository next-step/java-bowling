package bowling.domain.frame;

public enum State {
    STRIKE,
    SPARE,
    MISS,
    GUTTER;

    private int knockedDownPinCount;
}
