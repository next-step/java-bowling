package bowling;

import java.util.List;

public interface Frame {
    void bowl(Pin falledPins);

    Frame nextFrame(Pin falledPins);

    boolean isFinalFrame();

    boolean isFinished();

    public Score getScores();
}
