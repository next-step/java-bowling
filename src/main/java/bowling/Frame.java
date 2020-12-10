package bowling;

import java.util.List;

public interface Frame {
    void setKnockDownPins(int knockDownPins);

    List<Pitching> getStatus();

    boolean isEnd();
}
