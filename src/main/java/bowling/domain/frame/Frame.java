package bowling.domain.frame;

public interface Frame {
    int determineSpare(int fallenPins);

    int validateMoveToNextIndex();

    boolean equal(int index);

    int index();

    Pins pins();
}
