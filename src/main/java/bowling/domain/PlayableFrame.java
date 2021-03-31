package bowling.domain;

import java.util.List;

public interface PlayableFrame {

    FrameNumber number();

    void addPintCount(int pinCount);

    List<PinCount> pinCounts();

    boolean isDone();
}
