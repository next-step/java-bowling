package bowling;

import java.util.List;

public interface Frame {
    Frame initNextFrame();

    void setKnockDownPins(int knockDownPins);

    List<Pitching> getStatus();

    boolean isEnd();

    Frame getNextFrame();
}
