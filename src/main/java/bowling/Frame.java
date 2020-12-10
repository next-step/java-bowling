package bowling;

import java.util.List;

public interface Frame {
    Frame getNextFrame();

    void setKnockDownPins(int knockDownPins);

    List<Pitching> getStatus();

    boolean isEnd();
}
