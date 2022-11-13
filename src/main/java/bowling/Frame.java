package bowling;

import java.util.List;

public interface Frame {
    Frame bowl(Pin falledPins);

    Frame nextFrame(Pin falledPins);

    boolean isFinalFrame();

    boolean isFinished();

    public List<Pin> getScores();
}
