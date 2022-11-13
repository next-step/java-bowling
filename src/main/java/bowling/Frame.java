package bowling;

public interface Frame {
    Frame bowl(Pin falledPins);

    Frame nextFrame(Pin falledPins);

    boolean isFinalFrame();

    boolean isFinished();
}
