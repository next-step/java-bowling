package bowling.domain.frame;

public interface Frame {
    void determineSpare(int fallenPins);

    Frame validateMoveToNextFrame();

    int index();

    FallenPins pins();
}
