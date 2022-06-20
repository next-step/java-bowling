package bowling.domain.frame;

public interface Frame {
    void determineSpare(int fallenPins);

    int validateMoveToNextIndex();

    int index();

    Pins pins();
}
