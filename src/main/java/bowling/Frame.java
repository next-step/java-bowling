package bowling;

import java.util.List;

public interface Frame {
    void setKnockDownPins(int knockDownPins);

    void setKnockDownPins2(int knockDownPins);

    String getStatus();

    List<Pitching> getStatus2();

    boolean isEnd();
}
